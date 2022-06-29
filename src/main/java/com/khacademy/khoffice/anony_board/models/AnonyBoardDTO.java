package com.khacademy.khoffice.anony_board.models;

import java.util.Date;

public class AnonyBoardDTO {

	private int aboard_no;
	private String title;
	private String content;
	private Date reg_date;
	private int view_count;

	public AnonyBoardDTO() {
	}

	public int getAboard_no() {
		return aboard_no;
	}

	public void setAboard_no(int aboard_no) {
		this.aboard_no = aboard_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	@Override
	public String toString() {
		return "AnonyBoardDTO [aboard_no=" + aboard_no + ", title=" + title + ", content=" + content + ", reg_date="
				+ reg_date + ", view_count=" + view_count + "]";
	}

	
}