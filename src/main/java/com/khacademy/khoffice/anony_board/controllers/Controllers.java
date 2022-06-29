package com.khacademy.khoffice.anony_board.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.anony_board.models.AnonyBoardDTO;
import com.khacademy.khoffice.anony_board.services.AnonyBoardService;
import com.khacademy.khoffice.anony_comment.models.AnonyCommentDTO;
import com.khacademy.khoffice.anony_comment.services.AnonyCommentService;


@Controller("anony_boardAb")
@RequestMapping("/anony_board")
public class Controllers {
	private AnonyBoardService abservice;
	private AnonyCommentService acmService;

	@Autowired
	@Required	
	public void setAbservice(AnonyBoardService abservice) {
		this.abservice = abservice;
	}
	
	@Autowired
	@Required	
	public void setAcmService(AnonyCommentService acmService) {
		this.acmService = acmService;
	}

	// 게시글 목록
	@RequestMapping(method = RequestMethod.GET)
	public String getList(HttpServletRequest request,AnonyBoardDTO anonyBoardDTO, ModelMap modelMap) {
		String search_type = request.getParameter("search_type");
		if (search_type == null || search_type == "") {
			search_type = "";
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

		List<AnonyBoardDTO> list = abservice.boardList(map);
		int articleCount = 0;

		if (keyword != null) {
			map.put("keyword", keyword);
			map.put("search_type", search);
			list = abservice.listSearchBoard(map);
			articleCount = abservice.articleSearchCount(map);
			modelMap.addAttribute("keyword", keyword);
			modelMap.addAttribute("search_type", search_type);
		} else {
			list = abservice.boardList(map);
			articleCount = abservice.articleCount();
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
		
		return "tiles/anony_board/list";
	}

	// 게시글 등록
	@RequestMapping(method = RequestMethod.POST)
	public String add(@ModelAttribute("anonyBoardDTO") AnonyBoardDTO anonyBoardDTO, ModelMap modelMap) {
		abservice.boardInsert(anonyBoardDTO);
		int aboard_no = anonyBoardDTO.getAboard_no();
		return "redirect:/anony_board/"+aboard_no;
	}

	// 게시글 상세
		@RequestMapping(value = "{aboard_no}", method = RequestMethod.GET)
		public String getDetail(@PathVariable("aboard_no") int aboard_no, HttpServletRequest request, ModelMap modelMap)
				throws Exception {
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
			
			abservice.countUpdate(new Integer(aboard_no));
			AnonyBoardDTO dto = abservice.boardDetail(new Integer(aboard_no));
			
			SimpleDateFormat formatType= new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String reg_date1 = formatType.format(dto.getReg_date());
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date reg_date2 = (Date) transFormat.parse(reg_date1);
			dto.setReg_date(reg_date2);
			
			modelMap.addAttribute("board", dto);
			modelMap.addAttribute("view_count", aboard_no);
			modelMap.addAttribute("pageNum", pageNum);
			modelMap.addAttribute("keyword", keyword);
			modelMap.addAttribute("search_type", search_type);

			// 댓글 리스트 뿌리기
		      
		    int pageSize = 5; // 한페이지의 댓글의 개수
		    int currentPage = Integer.parseInt("1");
		    int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		    int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		      
		    map.put("startRow", startRow);
		    map.put("endRow", endRow);
		    map.put("aboard_no", aboard_no);
			
			List<AnonyCommentDTO> commentlist = acmService.acommentList(map);
			int comment_count = acmService.commentCount(new Integer(aboard_no));
			
			
			modelMap.addAttribute("commentlist", commentlist);
			modelMap.addAttribute("comment_count", comment_count);
			
			return "tiles/anony_board/detail";
		}


}
