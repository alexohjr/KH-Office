package com.khacademy.khoffice.depart_board.models;


import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class DepartBoardDTO {

	private int dboard_no;
	private String title;
	private String content;
	private String name;
	private String file_path;
	private MultipartFile file;
	private Date reg_date;
	private int view_count;
	private int department_no;
	private String department_name;
	private int member_no;
	private String position;
	private String thumb_path;

	public DepartBoardDTO() {
	}
	
	public String getThumb_path() {
		return thumb_path;
	}

	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getDboard_no() {
		return dboard_no;
	}

	public void setDboard_no(int dboard_no) {
		this.dboard_no = dboard_no;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
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

	public int getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "DepartBoardDTO [dboard_no=" + dboard_no + ", title=" + title + ", content=" + content + ", name=" + name
				+ ", file_path=" + file_path + ", file=" + file + ", reg_date=" + reg_date + ", view_count="
				+ view_count + ", department_no=" + department_no + ", department_name=" + department_name
				+ ", member_no=" + member_no + ", position=" + position + ", thumb_path=" + thumb_path + "]";
	}

}