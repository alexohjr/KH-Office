package com.khacademy.khoffice.member.models;

public class PagingDTO {
	private int articleSize; //한 페이지 내에서 보여지는 글의 개수
	private int articlecount; //글 갯수
	private int currentPage; //현재페이지
	private int startRow;  // 한 페이지의 시작글 번호
	private int endRow; // 한 페이지의 마지막 글번호
	private int pageBlock;// 한 페이지에서 보여줄 페이징 개수 (< 12345 >)
	private int pageCount; //총 페이징 갯수
	
	
}
