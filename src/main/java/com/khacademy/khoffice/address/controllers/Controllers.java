package com.khacademy.khoffice.address.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.address.models.AddressDTO;
import com.khacademy.khoffice.address.models.BookmarkDTO;
import com.khacademy.khoffice.address.models.EditAddressDTO;
import com.khacademy.khoffice.address.services.AddressService;



@Controller("addressController")
@RequestMapping("/address")
public class Controllers {
	private AddressService addressService;
	
	@Autowired
	@Required
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	//개인주소록리스트 불러오기
	@RequestMapping(method=RequestMethod.GET)
	public String getList(
			HttpServletRequest request,
			@RequestParam(defaultValue="1")String page_num,
			@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="0")int is_bookmark,
			ModelMap model
			) throws Exception {
		System.out.println("컨트롤러 들어옴");
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
		articleCount = addressService.addressCount(map);
		
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
	
		List<AddressDTO> list = null;
		list = addressService.getAddressList(map); // 전체리스트
		
		// System.out.println("리스트 : " + list.get(1).getAddress_no());
		if(member_no != 0) {
			List<BookmarkDTO> blist = null;
			blist = addressService.getBookmarkList(member_no); // 내 세션에 해당하는 리스트
			
			for(int j=0; j<list.size(); j++) { //1 , 2 , 3, ,4 ....
				for(int i=0; i<blist.size(); i++) { // 3,7 
					int bookmarkAddress_no = blist.get(i).getAddress_no(); //북마크된 address_no(pk)
					int listAddress_no = list.get(j).getAddress_no(); //가져온 리스트의 address_no (로그인한 이 사람의 즐찾한 address_no)
					
					if(list.get(j).getIs_bookmark() != 1) {
						list.get(j).setIs_bookmark(0); //default값 0 셋팅 
						//resultList.add(j,list.get(j));
					}
					if(listAddress_no == bookmarkAddress_no) {
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
		model.addAttribute("pageNum", page_num);
		model.addAttribute("keyword",keyword);
		model.addAttribute("isBookmark",is_bookmark);
		model.addAttribute("pageCount",pageCount);
		
		return "tiles/address/list";
		
	}
	
	//개인주소록추가
	@RequestMapping(method=RequestMethod.POST)
	public String add(AddressDTO addressDTO,HttpServletRequest request) {
		
		//세션에 저장된 member_no값 불러오기
		int member_no = 0;
		Object sess = request.getSession().getAttribute("session_memberNo"); 
		if(sess != null){
			member_no = (int)sess;
		}
		
		//addressDTO에 세션에서 저장해온 member_no Set하기
		addressDTO.setMember_no(member_no); 
		
		//service 접근
		int x = 0;
		x = addressService.insertAddress(addressDTO);
		
		//return할 주소 분기
		if(x > 0) {
			System.out.println("등록 성공");
			return "redirect:/address";
		}else { //등록실패 시 이동페이지 
			System.out.println("등록 실패");
		}
		return "redirect:/address";
	}
	
	//개인주소록 삭제
	@RequestMapping(value="{addressNo}",method=RequestMethod.POST)
	public String update(
			@PathVariable("addressNo") int address_no,
			@RequestParam String type,
			@RequestParam(defaultValue="1")String page_num,
			@RequestParam(defaultValue="")String keyword,
			@RequestParam(defaultValue="0")int is_bookmark,
			EditAddressDTO editAddressDTO,
			HttpServletRequest request) {
		
		String result = null; //리턴용 변수
		System.out.println("update컨트롤러 들어왔음");
		if(type.equals("edit")) {
			int x = 0;
			editAddressDTO.setEdit_address_no(address_no);
			x = addressService.updateAddress(editAddressDTO);
			if(x > 0) {
				System.out.println("수정 성공");
				result = "redirect:/address?page_num=" + page_num + "&keyword=" + keyword + "&is_bookmark" + is_bookmark;
			}
		}else if(type.equals("delete")) {
			int x = 0;
			x = addressService.deleteAddress(address_no);
			if(x > 0) {
				System.out.println("delete 성공");
				result = "redirect:/address?page_num=" + page_num + "&keyword=" + keyword + "&is_bookmark" + is_bookmark;
			}
		}
		
		return result;
	}
}


