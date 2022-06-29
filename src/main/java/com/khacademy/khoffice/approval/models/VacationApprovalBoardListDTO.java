package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class VacationApprovalBoardListDTO {

	private int vacapproval_no; // 승인번호 시퀀스
	private int member_no; // 나의 member_no값
	private String status; // 0: 결재진행중 , 1: 승인 , 2: 반려
	private int vacreport_no; // consult_report 참조키
	private String type;
	private Date reg_date;
	private String approval_name;
	private String report_name;
	private String approval_position;
	private String report_position;
	private String last_approval;

	@Override
	public String toString() {
		return "VacationApprovalBoardListDTO [vacapproval_no=" + vacapproval_no + ", member_no=" + member_no
				+ ", status=" + status + ", vacreport_no=" + vacreport_no + ", type=" + type + ", reg_date=" + reg_date
				+ ", approval_name=" + approval_name + ", report_name=" + report_name + "]";
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

	public int getVacapproval_no() {
		return vacapproval_no;
	}

	public void setVacapproval_no(int vacapproval_no) {
		this.vacapproval_no = vacapproval_no;
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

	public int getVacreport_no() {
		return vacreport_no;
	}

	public void setVacreport_no(int vacreport_no) {
		this.vacreport_no = vacreport_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
