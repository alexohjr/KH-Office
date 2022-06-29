package com.khacademy.khoffice.approval.models;

public class VacationApprovalUpdateStatusDTO {

	private int member_no;
	private String status;
	private int vacreport_no;
	private int vacapproval_no;
	private int use_day;
	private int report_member_no;

	@Override
	public String toString() {
		return "VacationApprovalUpdateStatusDTO [member_no=" + member_no + ", status=" + status + ", vacreport_no="
				+ vacreport_no + ", vacapproval_no=" + vacapproval_no + ", use_day=" + use_day + ", report_member_no="
				+ report_member_no + "]";
	}

	public int getReport_member_no() {
		return report_member_no;
	}

	public void setReport_member_no(int report_member_no) {
		this.report_member_no = report_member_no;
	}

	public int getUse_day() {
		return use_day;
	}

	public void setUse_day(int use_day) {
		this.use_day = use_day;
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

	public int getVacapproval_no() {
		return vacapproval_no;
	}

	public void setVacapproval_no(int vacapproval_no) {
		this.vacapproval_no = vacapproval_no;
	}

}
