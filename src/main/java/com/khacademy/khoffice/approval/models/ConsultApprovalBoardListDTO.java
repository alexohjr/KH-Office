package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class ConsultApprovalBoardListDTO {

	private int conapproval_no; // 승인번호 시퀀스
	private int member_no; // 나의 member_no값
	private String status; // 0: 대기중 , 1: 진행중 , 2: 반려
	private int conreport_no; // consult_report 참조키
	private String title;
	private Date reg_date;
	private String approval_name;
	private String report_name;
	private String approval_position;
	private String report_position;
	private String last_approval;

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

	@Override
	public String toString() {
		return "ConsultApprovalBoardDTO [conapproval_no=" + conapproval_no + ", member_no=" + member_no + ", status="
				+ status + ", conreport_no=" + conreport_no + ", title=" + title + ", reg_date=" + reg_date
				+ ", approval_name=" + approval_name + ", report_name=" + report_name + "]";
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

	public int getConapproval_no() {
		return conapproval_no;
	}

	public void setConapproval_no(int conapproval_no) {
		this.conapproval_no = conapproval_no;
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

	public int getConreport_no() {
		return conreport_no;
	}

	public void setConreport_no(int conreport_no) {
		this.conreport_no = conreport_no;
	}

}
