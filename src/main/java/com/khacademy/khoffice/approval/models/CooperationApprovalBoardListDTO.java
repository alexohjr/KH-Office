package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class CooperationApprovalBoardListDTO {

	private int cooapproval_no; // 승인번호 시퀀스
	private int member_no; // 나의 member_no값
	private String status; // 0: 결재진행중 , 1: 승인 , 2: 반려
	private int cooreport_no; // consult_report 참조키
	private String title;
	private Date reg_date;
	private String approval_name;
	private String report_name;
	private String approval_position;
	private String report_position;
	private String last_approval;

	@Override
	public String toString() {
		return "CooperationApprovalBoardListDTO [cooapproval_no=" + cooapproval_no + ", member_no=" + member_no
				+ ", status=" + status + ", cooreport_no=" + cooreport_no + ", title=" + title + ", reg_date="
				+ reg_date + ", approval_name=" + approval_name + ", report_name=" + report_name + "]";
	}

	public String getApproval_position() {
		return approval_position;
	}

	public void setApproval_position(String approval_position) {
		this.approval_position = approval_position;
	}

	public String getReport_position() {
		return report_position;
	}

	public void setReport_position(String report_position) {
		this.report_position = report_position;
	}

	public String getLast_approval() {
		return last_approval;
	}

	public void setLast_approval(String last_approval) {
		this.last_approval = last_approval;
	}

	public int getCooapproval_no() {
		return cooapproval_no;
	}

	public void setCooapproval_no(int cooapproval_no) {
		this.cooapproval_no = cooapproval_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCooreport_no() {
		return cooreport_no;
	}

	public void setCooreport_no(int cooreport_no) {
		this.cooreport_no = cooreport_no;
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

	public String getApproval_name() {
		return approval_name;
	}

	public void setApproval_name(String approval_name) {
		this.approval_name = approval_name;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

}
