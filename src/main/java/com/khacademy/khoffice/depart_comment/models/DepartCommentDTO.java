package com.khacademy.khoffice.depart_comment.models;

import java.sql.Date;

public class DepartCommentDTO {

	private int dcomment_no;
	private int member_no;
	private String content;
	private Date reg_date;
	private int dboard_no;
	private String name;
	private String position;
	private String thumb_path;
	private int department_no;
	
	
	public String getThumb_path() {
		return thumb_path;
	}

	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}
	
	public DepartCommentDTO() {
	}

	public int getDcomment_no() {
		return dcomment_no;
	}



	public void setDcomment_no(int dcomment_no) {
		this.dcomment_no = dcomment_no;
	}



	public int getMember_no() {
		return member_no;
	}



	public void setMember_no(int member_no) {
		this.member_no = member_no;
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



	public int getDboard_no() {
		return dboard_no;
	}



	public void setDboard_no(int dboard_no) {
		this.dboard_no = dboard_no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public int getDepartment_no() {
		return department_no;
	}



	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}



	@Override
	public String toString() {
		return "DepartCommentDTO [dcomment_no=" + dcomment_no + ", member_no=" + member_no + ", content=" + content
				+ ", reg_date=" + reg_date + ", dboard_no=" + dboard_no + ", name=" + name + ", position=" + position
				+ ", department_no=" + department_no + "]";
	}

}
