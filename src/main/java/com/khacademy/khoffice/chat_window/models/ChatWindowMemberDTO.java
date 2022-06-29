package com.khacademy.khoffice.chat_window.models;

import java.util.List;

public class ChatWindowMemberDTO {
	private int cwindow_no;
	private String name;
	private List<Integer>member_no;
	
	public int getCwindow_no() {
		return cwindow_no;
	}
	public void setCwindow_no(int cwindow_no) {
		this.cwindow_no = cwindow_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getMember_no() {
		return member_no;
	}
	public void setMember_no(List<Integer> member_no) {
		this.member_no = member_no;
	}
}
