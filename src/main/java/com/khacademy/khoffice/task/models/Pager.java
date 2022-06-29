package com.khacademy.khoffice.task.models;

public class Pager {
	private int postCount; // postCount 가져온 글 수
	private int pageCount; // pageCount 전체 페이지 수
	private int startPager; // startPager 페이저 시작수
	private int endPager; // endPager 페이저 끝수
	private int start;
	private int end;
	private int pageSize;
	private int pagerSize;
	private int sessionId;
	private String queryString;
	private int page_num;
	private String keyword;
	private String type;
	
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPager() {
		return startPager;
	}
	public void setStartPager(int startPager) {
		this.startPager = startPager;
	}
	public int getEndPager() {
		return endPager;
	}
	public void setEndPager(int endPager) {
		this.endPager = endPager;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPagerSize() {
		return pagerSize;
	}
	public void setPagerSize(int pagerSize) {
		this.pagerSize = pagerSize;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public int getPage_num() {
		return page_num;
	}
	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
