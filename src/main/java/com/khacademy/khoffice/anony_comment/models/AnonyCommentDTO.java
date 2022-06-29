package com.khacademy.khoffice.anony_comment.models;

import java.sql.Date;

public class AnonyCommentDTO {

	private int acomment_no;
	private String content;
	private Date reg_date;
	private int aboard_no;

	public AnonyCommentDTO() {
	}

	public int getAcomment_no() {
		return acomment_no;
	}

	public void setAcomment_no(int acomment_no) {
		this.acomment_no = acomment_no;
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

	public int getAboard_no() {
		return aboard_no;
	}

	public void setAboard_no(int aboard_no) {
		this.aboard_no = aboard_no;
	}

	@Override
	public String toString() {
		return "AnonyCommentDTO [acomment_no=" + acomment_no + ", content=" + content + ", reg_date=" + reg_date
				+ ", aboard_no=" + aboard_no + "]";
	}

	
}
