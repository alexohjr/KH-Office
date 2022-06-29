package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class ConsultReportBoardListDTO {

	private int conreport_no;
	private String title;
	private Date reg_date;
	private String name;
	private String status;
	private int member_no;
	private String content;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		
		if(status.equals("0")) {
			status = "대기중";
		} else if (status.equals("1")) {
			status = "진행중"; // 승인
		} else if (status.equals("2")) {
			status = "반려";
		} else if (status.equals("3")){
			status = "결재완료";
		}
		
		this.status = status;
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

	public int getConreport_no() {
		return conreport_no;
	}

	public void setConreport_no(int conreport_no) {
		this.conreport_no = conreport_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
