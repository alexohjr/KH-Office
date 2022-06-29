package com.khacademy.khoffice.member.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.khacademy.khoffice.member.models.BookmarkDTO;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.models.MemberDepartmentDTO;
import com.khacademy.khoffice.member.models.MemberDetailDTO;
import com.khacademy.khoffice.member.models.PasswordDTO;
import com.khacademy.khoffice.member.services.MemberService;


@Controller("memberController")
@RequestMapping("/member")
public class Controllers {
	private MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	   //멤버리스트 불러오기
	   @RequestMapping(method=RequestMethod.GET)
	   public String getList(
	         HttpServletRequest request,
	         @RequestParam(defaultValue="1")String page_num,
	         @RequestParam(defaultValue="")String keyword,
	         @RequestParam(defaultValue="0")int is_bookmark,
	         ModelMap model
	         ) throws Exception {
	      
	      int articleSize = 10; // 한 페이지 내에서 보여지는 글의 개수
	      int currentPage = Integer.parseInt(page_num);
	      int startRow = (currentPage - 1) * articleSize + 1; // 한 페이지의 시작글 번호
	      int endRow = currentPage * articleSize; // 한 페이지의 마지막 글번호
	      int articleCount = 0;
	      int member_no = 0;
	      Object sess = request.getSession().getAttribute("session_memberNo"); //세션에 저장된 member_no값 불러오기
	      if(sess != null){
	         member_no = (int)sess;
	      }
	
	
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("keyword", keyword);
	      map.put("member_no", member_no);
	      map.put("is_bookmark", is_bookmark);
	      articleCount = memberService.memberCount(map);
	      
	      System.out.println("page_num : " + page_num);
	      System.out.println("keyword : " + keyword);
	      System.out.println("is_bookmark : " + is_bookmark);
	      System.out.println("articleCount : " + articleCount);
	      System.out.println("======================");
	      
	      int pageBlock = 5; // 한 페이지에서 보여줄 페이징 개수 (< 12345 >)
	      int pageCount = articleCount / articleSize + (articleCount % articleSize  == 0 ? 0 : 1 );
	      int startPage = ((currentPage-1) / pageBlock) * pageBlock + 1; //시작 페이징 번호 
	      int endPage = startPage + pageBlock - 1; //끝 페이징 번호
	      if(endPage > pageCount) {
	         endPage = pageCount;
	      }
	
	      map.put("start", startRow);
	      map.put("end", endRow);
	   
	      List<MemberDepartmentDTO> list = null;
	      list = memberService.getMemberList(map); // 전체리스트
	      
	      /*String adminId = (String)request.getSession().getAttribute("session_adminID");
	      if(adminId != "0") {*/
	      if(member_no != 0) {
	         List<BookmarkDTO> blist = null;
	         blist = memberService.getBookmarkList(member_no); // 내 세션에 해당하는 리스트
	         
	         for(int j=0; j<list.size(); j++) { //1 , 2 , 3, ,4 ....
	            for(int i=0; i<blist.size(); i++) { // 3,7 
	               int bookmarkBmember_no = blist.get(i).getBmember_no();
	               int listMember_no = list.get(j).getMember_no();
	               
	               if(list.get(j).getIs_bookmark() != 1) {
	                  list.get(j).setIs_bookmark(0); //default값 0 셋팅 
	                  //resultList.add(j,list.get(j));
	               }
	               if(listMember_no == bookmarkBmember_no) {
	                  list.get(j).setIs_bookmark(1);
	               }
	            }
	         }
	      }
	      //view에 보여질 model 데이터 넣기
	      model.addAttribute("list", list);             
	      model.addAttribute("startPage", startPage);
	      model.addAttribute("endPage", endPage);
	      model.addAttribute("count", articleCount);
	      model.addAttribute("pageCount", pageCount);
	      model.addAttribute("pageNum", page_num);
	      model.addAttribute("keyword",keyword);
	      model.addAttribute("isBookmark",is_bookmark);
	      
	      return "tiles/member/list";
	   }
	
	//사원 상세페이지
	@RequestMapping(value="{memberNo}", method=RequestMethod.GET)
	public String getDetail(
			@PathVariable("memberNo") int member_no,
			@RequestParam(defaultValue="1")String page_num,
			@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="0")int is_bookmark,
			ModelMap model,HttpServletRequest request){
		//세션에 저장된 값 받아오기
		HttpSession session = request.getSession();	
		String session_adminID = (String)session.getAttribute("session_adminID");
		int session_memberNo = 0;
		Object sess = request.getSession().getAttribute("session_memberNo"); //세션에 저장된 member_no값 불러오기
		if(sess != null){
			session_memberNo = (int)sess;
		}

		String result = null;
		if(session_adminID == null && session_memberNo != member_no){ //세션에 어드민id가 없거나 접근하려는 상세페이지 사번과 세션의 사번이 다르면
			result="tiles/member/accessfail";
		}else if(session_adminID != null || session_memberNo == member_no){ //어드민이거나 접근하려는 상세페이지 사번과 세션의 사번이 같으면
			List<MemberDetailDTO> list = null;
			list = memberService.getMemberDetail(member_no);
			model.addAttribute("list",list);
			model.addAttribute("pageNum", page_num);
			model.addAttribute("keyword", keyword);
			model.addAttribute("isBookmark", is_bookmark);
			result = "tiles/member/detail";
		} 
		return result;
	}
	
	//사원 등록하기
	@RequestMapping(method=RequestMethod.POST)
	public String add(MemberDTO memberDTO, MultipartHttpServletRequest request) {
		System.out.println("들어옴");
			MultipartFile uploadFile = request.getFile("uploadFile");
			
			String thumb_path = fileUpload(request, uploadFile);
			System.out.println("thumb_path : " + thumb_path);
			memberDTO.setThumb_path(thumb_path);
			System.out.println("입사일 : " + memberDTO.getHire_date());
			System.out.println("생일 : " + memberDTO.getBirthday());
			int x = 0;
			x = memberService.memberRegister(memberDTO);
			if(x > 0) {
				System.out.println("insert 성공");
			}else {
				System.out.println("insert 실패");
			}
			int member_no = memberDTO.getMember_no();
		
		return "redirect:/member/" + member_no;
		//return "redirect:/member";
	}
	  
	//파일업로드용 메소드 
	public String fileUpload(MultipartHttpServletRequest request, MultipartFile uploadFile) {

		String realPath = request.getServletContext().getRealPath("/");
		String path = "resources/upload/profile/";
		String fileName = ""; //file.getOriginalFilename();
		String fullPath = "";
		OutputStream out = null;
	    PrintWriter printWriter = null;
		
		try {
			fileName = uploadFile.getOriginalFilename(); 
			byte[] bytes = uploadFile.getBytes();
			
			if(fileName != null && !fileName.equals("")) { //파일명이 있으면 파일업로드 
				File imgFile = new File(realPath + path + fileName);
				System.out.println(imgFile.exists());
				
				//파일명이 중복일 경우
				if(imgFile.exists()) {
					fileName = System.currentTimeMillis() + "_" + fileName; // 파일명 앞에 업로드 시간 초단위로 붙여 파일명 중복방지
					imgFile = new File(realPath + path + fileName);
				}
				
				System.out.println("path : " + path);
				System.out.println("name : " + fileName);
				fullPath = path + fileName;
				out = new FileOutputStream(imgFile);
				out.write(bytes);
			} else if(fileName == null || fileName.equals("")) { //파일명이 없으면 기본이미지명으로 지정 
				fileName = "noimage.jpg";
				path = "resources/icon/";
				fullPath = path + fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null) {
					out.close();
				}
				if(printWriter != null) {
					printWriter.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		} 
		return fullPath;
	}
	
	//비밀번호 변경하기 , 사원 수정하기
	@RequestMapping(value="{memberNo}",method=RequestMethod.POST)
	public String update(
			@PathVariable("memberNo") int member_no,
			@RequestParam String type,
			@RequestParam(defaultValue="1")String page_num,
			@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="0")int is_bookmark,
			MultipartHttpServletRequest request,
			PasswordDTO passwordDTO,
			MemberDTO memberDTO
			) {
		String result = null; //리턴용 변수 선언
		System.out.println("type : " + type);
			
		if(type.equals("edit")) {
			
			//업로드한 파일 받아오기
			MultipartFile uploadFile = request.getFile("uploadFile");
	
			//업로드한 파일의 파일명 받아오기 
			String fileName = uploadFile.getOriginalFilename(); 
			System.out.println("업로드한 파일명 : " + fileName);
			//thumb_path를 set 하기 위한 변수 선언
			String thumb_path = null;
			
			//수정하려는 member_no로 기존에 저장되어있는 thumb_path값 받아오기
			String checkThumbPath = null;
			checkThumbPath = memberService.getMemberThumbPath(member_no);
			System.out.println("사번 : " + member_no);
			System.out.println("기존에 저장되어있는 패스 : " + checkThumbPath );
			if(!fileName.equals("")) { //업로드한 파일의 파일명이 null이 아니면 (사진을 업로드했으면)
				thumb_path = fileUpload(request, uploadFile); //새로운 thumb_path 저장
			}if(fileName.equals("")) { //업로드한 파일의 파일명이 null이면 (사진 변경 안했으면)
				thumb_path = checkThumbPath; //기존의 thumb_path를 그대로 유지
			}
			
			memberDTO.setThumb_path(thumb_path); //thumb_path set하기
			memberDTO.setMember_no(member_no); 
			
			int x = 0;
			x = memberService.updateMember(memberDTO);
			
			if(x > 0) {
				System.out.println("사원 수정 성공");
				result = "redirect:/member/" + member_no + "?page_num=" + page_num + "&keyword=" + keyword + "&is_bookmark=" + is_bookmark;
			}else {
				System.out.println("사원 수정 실패");
				result = "tiles/member/changefail";
			}
			
		} else if (type.equals("change_pw")) {
			// 아예 파일파라미터가 없고, 일반 파라미터만 들어온 경우임
			passwordDTO.setMemberNo(member_no);
			
			int x = 0;
			x = memberService.changePassword(passwordDTO);
			System.out.println("member_no : " + member_no + ", x : " + x);
			if(x > 0) {
				System.out.println("비밀번호 update 성공");
				result = "redirect:/member/" + member_no;
			}else {
				System.out.println("비밀번호 update 실패");
				result = "tiles/member/changefail";
			}
		} else if (type.equals("delete")) {
			System.out.println("type은 delete");
			int x = 0;
			x = memberService.deleteMember(member_no);
			if(x > 0) {
				System.out.println("delete 성공");
				result = "redirect:/member?page_num=" + page_num + "&keyword=" + keyword + "&is_bookmark=" + is_bookmark;
			}else {
				System.out.println("delete 실패");
				result = "tiles/member/changefail";
			}
			
		}
		
		System.out.println("result : " + result);
		
		return result;
	}
	
	@InitBinder
	   protected void initBinder(WebDataBinder binder) {
	      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	   }
	
}




















/*
String result = null; //리턴용 변수 선언
passwordDTO.setMemberNo(member_no);
System.out.println("컨트롤러 들어옴");

if(type.equals("change_pw")) {
	int x = 0;
	x = memberService.changePassword(passwordDTO);
	System.out.println("member_no : " + member_no + ", x : " + x);
	if(x > 0) {
		System.out.println("비밀번호 update 성공");
		result = "redirect:/member/" + member_no;
	}else {
		System.out.println("비밀번호 update 실패");
		result = "tiles/member/changefail";
	}

} else if(type.equals("edit")) {
	int x = 0;
	Map<String, Object> map = new HashMap<String, Object>();
	System.out.println("if문 들어옴");
	MultipartFile uploadFile = request.getFile("uploadFile");

	String thumb_path = fileUpload(request, uploadFile);
	System.out.println("thumb_path : " + thumb_path);
	memberDTO.setThumb_path(thumb_path);
	memberDTO.setMember_no(member_no);
	
	map.put("keyword", keyword);
	map.put("pageNum",page_num);
	map.put("isBookmark",is_bookmark);
	map.put("memberDTO",memberDTO);
	x = memberService.updateMember(map);
	if(x > 0) {
		System.out.println("사원 수정 성공");
		result = "redirect:/member/" + member_no + "?page_num=" + page_num + "&keyword=" + keyword + "&is_bookmark=" + is_bookmark;
	}else {
		System.out.println("사원 수정 실패");
		result = "tiles/member/changefail";
	}
}

System.out.println("result : " + result);

return result;
*/
