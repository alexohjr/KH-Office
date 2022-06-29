package com.khacademy.khoffice.depart_board.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;
import com.khacademy.khoffice.depart_board.services.DepartBoardService;
import com.khacademy.khoffice.depart_comment.models.DepartCommentDTO;
import com.khacademy.khoffice.depart_comment.services.DepartCommentService;

@Controller("depart_boardDb")
@RequestMapping("/depart_board")
public class Controllers {
	private DepartBoardService dbservice;
	private DepartCommentService dcmService;

	@Autowired
	@Required
	public void setDbService(DepartBoardService dbservice) {
		this.dbservice = dbservice;
	}

	@Autowired
	@Required
	public void setDcmService(DepartCommentService dcmService) {
		this.dcmService = dcmService;
	}

	// 게시글 목록
	@RequestMapping(method = RequestMethod.GET)
	public String getList(HttpServletRequest request,DepartBoardDTO departBoardDTO, ModelMap modelMap,HttpSession session) {
		int member_no = (int)session.getAttribute("session_memberNo");
		String depart_name=dbservice.getDepartmentName(member_no);
		
		String search_type = request.getParameter("search_type");
		if (search_type == null || search_type == "") {
			search_type = "0";
		}

		String search = null;

		String pageNum = request.getParameter("pageNum");
		String keyword = request.getParameter("keyword");

		if (search_type.equals("0")) {
			search = "title";
		} else if (search_type.equals("1")) {
			search = "content";
		} else if (search_type.equals("2")) {
			search = "name";
		}
		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int number = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", startRow);
		map.put("end", endRow);
		map.put("member_no", member_no);

		List<DepartBoardDTO> list = dbservice.boardList(map);
		int articleCount = 0;

		if (keyword != null) {
			map.put("keyword", keyword);
			map.put("search_type", search);
			list = dbservice.listSearchBoard(map);
			articleCount = dbservice.articleSearchCount(map);
			modelMap.addAttribute("keyword", keyword);
			modelMap.addAttribute("search_type", search_type);
		} else {
			list = dbservice.boardList(map);
			articleCount = dbservice.articleCount();
		}

		number = articleCount - (currentPage - 1) * pageSize;// 글목록에 표시할 글번호

		modelMap.addAttribute("list", list);
		modelMap.addAttribute("pageSize", pageSize);
		modelMap.addAttribute("currentPage", currentPage);
		modelMap.addAttribute("startRow", startRow);
		modelMap.addAttribute("endRow", endRow);
		modelMap.addAttribute("pageNum", pageNum);
		modelMap.addAttribute("count", articleCount);
		modelMap.addAttribute("number", number);
		modelMap.addAttribute("search_type", search_type);
		modelMap.addAttribute("depart_name", depart_name);		
		return "tiles/depart_board/list";
	}

	// 게시글 등록
	@RequestMapping(method = RequestMethod.POST)
	public String add(HttpServletRequest request, @ModelAttribute("departBoardDTO") DepartBoardDTO departBoardDTO, ModelMap modelMap,HttpSession session) {
		int member_no = (int)session.getAttribute("session_memberNo");
		departBoardDTO.setMember_no(member_no);
		DepartBoardDTO member = dbservice.membertb(member_no);
		departBoardDTO.setDepartment_no(member.getDepartment_no());

		MultipartFile fileup = departBoardDTO.getFile();
		if (fileup.getSize() > 0) {
			String file_path = fileup.getOriginalFilename();
			long now = System.currentTimeMillis();
			Random r = new Random();
			int i = r.nextInt(50);
			file_path = now + i + "_" + file_path;

			departBoardDTO.setFile_path(file_path);

			String realPath = request.getServletContext().getRealPath("/");
			String path = "resources/upload/board/";
			String fullPath = realPath + path;
			
			try {
				File new_file = new File(fullPath + file_path);
				fileup.transferTo(new_file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			departBoardDTO.setFile_path("");
		}
		
		dbservice.boardInsert(departBoardDTO);		
		int dboard_no = departBoardDTO.getDboard_no();
		
		return "redirect:/depart_board/"+dboard_no;
	}

	// 게시글 상세
	@RequestMapping(value = "{dboard_no}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("dboard_no") int dboard_no, HttpServletRequest request, ModelMap modelMap,HttpSession session)
			throws Exception {
		int member_no = (int)session.getAttribute("session_memberNo");
		Map<String,Object> map = new HashMap<String, Object>();

		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || pageNum == "") {
			pageNum = "1";
		}
		
		String keyword = request.getParameter("keyword");
		if(keyword==null || keyword=="") {
			keyword="";
		}
		
		String search_type = request.getParameter("search_type");
		if (search_type == null || search_type == "") {
			search_type = "0";
		}
		
		String depart_name=dbservice.getDepartmentName(member_no);
		int x = dbservice.countUpdate(new Integer(dboard_no));
		DepartBoardDTO dto = dbservice.boardDetail(new Integer(dboard_no));
		
		SimpleDateFormat formatType= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String reg_date1 = formatType.format(dto.getReg_date());
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date reg_date2 = (Date) transFormat.parse(reg_date1);
		dto.setReg_date(reg_date2);
		
		// 파일명 원래대로 만들기
		if (dto.getFile_path() != null) {
			String fileName = dto.getFile_path();
			int index = fileName.indexOf("_");
			String downloadFileName = fileName.substring(index+1);
			dto.setFile_path(downloadFileName);
		}
		System.out.println("Gdgd :: " + dto.getThumb_path());
		
		modelMap.addAttribute("board", dto);
		modelMap.addAttribute("view_count", x);
		modelMap.addAttribute("pageNum", pageNum);
		modelMap.addAttribute("keyword", keyword);
		modelMap.addAttribute("search_type", search_type);
		modelMap.addAttribute("depart_name", depart_name);	

		// 프로젝트 댓글 리스트 뿌리기
	      
	    int pageSize = 5; // 한페이지의 댓글의 개수
	    int currentPage = Integer.parseInt("1");
	    int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
	    int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
	      
	    map.put("startRow", startRow);
	    map.put("endRow", endRow);  
	    map.put("dboard_no", dboard_no);
	    /*projectCommentList = projectCommentService.getProjectCommentList(map);*/
		
		List<DepartCommentDTO> commentlist = dcmService.dcommentList(map);
		int comment_count = dcmService.commentCount(new Integer(dboard_no));
		
		
		modelMap.addAttribute("commentlist", commentlist);
		modelMap.addAttribute("comment_count", comment_count);
		modelMap.addAttribute("member_no", member_no);
		
		return "tiles/depart_board/detail";
	}

	// 게시글 수정
	@RequestMapping(value = "/{dboard_no}", method = RequestMethod.POST)
	public String update(@PathVariable("dboard_no") int dboard_no, DepartBoardDTO departBoardDTO, HttpServletRequest request,HttpSession session) throws Exception {
		int member_no = (int)session.getAttribute("session_memberNo");
		departBoardDTO.setMember_no(member_no);
		DepartBoardDTO member = dbservice.membertb(member_no);
		departBoardDTO.setDepartment_no(member.getDepartment_no());
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null || pageNum == "") {
			pageNum = "1";
		}
		
		String keyword = request.getParameter("keyword");
		if(keyword==null || keyword=="") {
			keyword="";
		}
		String search_type = request.getParameter("search_type");
		
		if (search_type == null || search_type == "") {
			search_type = "0";
		}
		
		String type = request.getParameter("type");

		if (type == "edit" || type.equals("edit")) {
			int dboard = departBoardDTO.getDboard_no();
			try {
				
				MultipartFile cFile =  departBoardDTO.getFile();
				if (cFile.getSize() > 0) {
					MultipartFile fileup = departBoardDTO.getFile();
					String file_path = fileup.getOriginalFilename();
					long now = System.currentTimeMillis();
					Random r = new Random();
					int i = r.nextInt(50);
					file_path = now + i + "_" + file_path;

					departBoardDTO.setFile_path(file_path);

					String realPath = request.getServletContext().getRealPath("/");
					String path = "resources/upload/board/";
					String fullPath = realPath + path;
					
					File new_file = new File(fullPath + file_path);
					fileup.transferTo(new_file);
					
				} 
				dbservice.boardUpdate(departBoardDTO);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/depart_board/"+dboard+"?pageNum="+pageNum+"&search_type="+search_type+"&keyword="+keyword;

		} else if (type == "delete" || type.equals("delete")) {
			dbservice.boardDelete(dboard_no);
		
		}
		return "redirect:/depart_board?pageNum="+pageNum+"&search_type="+search_type+"&keyword="+keyword;
		
	}
}
