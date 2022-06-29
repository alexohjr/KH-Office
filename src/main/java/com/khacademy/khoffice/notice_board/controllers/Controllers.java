package com.khacademy.khoffice.notice_board.controllers;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.khacademy.khoffice.notice_board.models.NoticeBoardDTO;
import com.khacademy.khoffice.notice_board.services.NoticeBoardService;

@Controller("notice_boardNb")
@RequestMapping("/notice_board")
public class Controllers {

	private NoticeBoardService nbservice;

	@Autowired
	@Required
	public void setNbService(NoticeBoardService nbservice) {
		this.nbservice = nbservice;
	}

	// 게시글 목록
	@RequestMapping(method = RequestMethod.GET)
	public String getList(HttpServletRequest request, HttpSession session, NoticeBoardDTO noticeBoardDTO, ModelMap modelMap) {
		String adminId = (String) session.getAttribute("adminId");
		modelMap.addAttribute("adminId", adminId);
		
		String search_type = request.getParameter("search_type");
		if (search_type == null || search_type == "") {
			search_type = "";
		}

		String search = null;

		String pageNum = request.getParameter("pageNum");
		String keyword = request.getParameter("keyword");
		
		Map<String, Object> map = new HashMap<String, Object>();

		if (search_type.equals("0")) {
			search = "title";
			map.put("search_type", "title");
			map.put("title", noticeBoardDTO.getTitle());
		} else if (search_type.equals("1")) {
			search = "content";
			map.put("search_type", "content");
			map.put("content", noticeBoardDTO.getContent());
		}
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}

		int pageSize = 10; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int number = 0;

		map.put("start", startRow);
		map.put("end", endRow);

		List<NoticeBoardDTO> list = null;
		int articleCount = 0;

		List<NoticeBoardDTO> Top = nbservice.ListIsTop();

		if (keyword != null) {
			map.put("keyword", keyword);
			map.put("search_type", search);
			list = nbservice.listSearchBoard(map);
			articleCount = nbservice.articleSearchCount(map);
			modelMap.addAttribute("keyword", keyword);
			modelMap.addAttribute("search_type", search_type);
		} else {
			list = nbservice.boardList(map);
			articleCount = nbservice.articleCount();
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
		modelMap.addAttribute("Top", Top);

		return "tiles/notice_board/list";
	}

	// 게시글 등록
	@RequestMapping(method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpSession session, NoticeBoardDTO noticeBoardDTO, ModelMap modelMap) {
		String adminId = (String) session.getAttribute("adminId");
		modelMap.addAttribute("adminId", adminId);
		
		if (noticeBoardDTO.getIs_top() == null || noticeBoardDTO.getIs_top() == "") {
			noticeBoardDTO.setIs_top("0");
		} else {
			noticeBoardDTO.setIs_top("1");
		}

		MultipartFile fileup = noticeBoardDTO.getFile();
		if (fileup.getSize() > 0) {
			String file_path = fileup.getOriginalFilename();
			long now = System.currentTimeMillis();
			Random r = new Random();
			int i = r.nextInt(50);
			file_path = now + i + "_" + file_path;

			noticeBoardDTO.setFile_path(file_path);

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
			noticeBoardDTO.setFile_path("");
		}

		nbservice.boardInsert(noticeBoardDTO);
		int nboard_no = noticeBoardDTO.getNboard_no();
		
		return "redirect:/notice_board/" + nboard_no;
	}

	// 게시글 상세
	@RequestMapping(value = "{nboard_no}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("nboard_no") int nboard_no, HttpServletRequest request, HttpSession session, ModelMap modelMap)
			throws Exception {
		
		String adminId = (String) session.getAttribute("adminId");
		modelMap.addAttribute("adminId", adminId);
		 
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}

		String keyword = request.getParameter("keyword");
		if (keyword == null || keyword == "") {
			keyword = "";
		}

		String search_type = request.getParameter("search_type");
		if (search_type == null || search_type == "") {
			search_type = "0";
		}

		int x = nbservice.countUpdate(new Integer(nboard_no));
		NoticeBoardDTO dto = nbservice.boardDetail(new Integer(nboard_no));

		SimpleDateFormat formatType= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String reg_date1 = formatType.format(dto.getReg_date());
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date reg_date2 = (Date) transFormat.parse(reg_date1);
		dto.setReg_date(reg_date2);
		
		// 파일명 원래대로 만들기
		if (dto.getFile_path() != null) {
			String fileName = dto.getFile_path();
			int index = fileName.indexOf("_");
			String downloadFileName = fileName.substring(index + 1);
			dto.setFile_path(downloadFileName);
		}

		modelMap.addAttribute("board", dto);
		modelMap.addAttribute("view_count", x);
		modelMap.addAttribute("pageNum", pageNum);
		modelMap.addAttribute("keyword", keyword);
		modelMap.addAttribute("search_type", search_type);

		return "tiles/notice_board/detail";
	}

	// 게시글 수정
	@RequestMapping(value = "/{nboard_no}", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session, @PathVariable("nboard_no") int nboard_no, NoticeBoardDTO noticeBoardDTO, ModelMap modelMap) throws Exception {
		
		String adminId = (String) session.getAttribute("adminId");
		modelMap.addAttribute("adminId", adminId);
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}

		String keyword = request.getParameter("keyword");
		if (keyword == null || keyword == "") {
			keyword = "";
		}
		String search_type = request.getParameter("search_type");

		if (search_type == null || search_type == "") {
			search_type = "0";
		}

		String type = request.getParameter("type");

		if (type == "edit" || type.equals("edit")) {

			if (noticeBoardDTO.getIs_top() == null || noticeBoardDTO.getIs_top() == "") {
				noticeBoardDTO.setIs_top("0");
			} else {
				noticeBoardDTO.setIs_top("1");
			}

			int nboard = noticeBoardDTO.getNboard_no();
			// int x = nbservice.boardUpdate(departBoardDTO);
try {
				
				MultipartFile cFile =  noticeBoardDTO.getFile();
				if (cFile.getSize() > 0) {
					MultipartFile fileup = noticeBoardDTO.getFile();
					String file_path = fileup.getOriginalFilename();
					long now = System.currentTimeMillis();
					Random r = new Random();
					int i = r.nextInt(50);
					file_path = now + i + "_" + file_path;

					noticeBoardDTO.setFile_path(file_path);

					String realPath = request.getServletContext().getRealPath("/");
					String path = "resources/upload/board/";
					String fullPath = realPath + path;
					
					File new_file = new File(fullPath + file_path);
					fileup.transferTo(new_file);
					
				} 
				nbservice.boardUpdate(noticeBoardDTO);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "redirect:/notice_board/" + nboard + "?pageNum=" + pageNum + "&search_type=" + search_type
					+ "&keyword=" + keyword;

		} else if (type == "delete" || type.equals("delete")) {
			nbservice.boardDelete(nboard_no);

		}
		return "redirect:/notice_board?pageNum=" + pageNum + "&search_type=" + search_type + "&keyword=" + keyword;

	}
}
