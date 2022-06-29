package com.khacademy.khoffice.project_comment.models;

import java.sql.Date;

public class ProjectCommentDTO {

	private int pcomment_no;
	private int pmember_no;
	private String content;
	private Date reg_date;
	private int project_no;
	
	private int member_no;
	private String name;
	private String position;
	private String thumb_path;

	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
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
	public String getThumb_path() {
		return thumb_path;
	}
	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}

	public int getPcomment_no() {
		return pcomment_no;
	}
	public void setPcomment_no(int pcomment_no) {
		this.pcomment_no = pcomment_no;
	}
	public int getPmember_no() {
		return pmember_no;
	}
	public void setPmember_no(int pmember_no) {
		this.pmember_no = pmember_no;
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
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
}
