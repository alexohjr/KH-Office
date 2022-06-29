package com.khacademy.khoffice.notice_board.models;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoticeBoardDTO {

	private int nboard_no;
	private String title;
	private String content;
	private String file_path;
	private MultipartFile file;
	private Date reg_date;
	private int view_count;
	private String is_top;

	public NoticeBoardDTO() {
	}

	public int getNboard_no() {
		return nboard_no;
	}

	public void setNboard_no(int nboard_no) {
		this.nboard_no = nboard_no;
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

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public String getIs_top() {
		return is_top;
	}

	public void setIs_top(String is_top) {
		this.is_top = is_top;
	}

	@Override
	public String toString() {
		return "NoticeBoardDTO [nboard_no=" + nboard_no + ", title=" + title + ", content=" + content + ", file_path="
				+ file_path + ", file=" + file + ", reg_date=" + reg_date + ", view_count=" + view_count + ", is_top="
				+ is_top + "]";
	}
	
	

	
}