package com.khacademy.khoffice.approval.models;

public class ConsultApprovalUpdateStatusDTO {

	private int member_no;
	private String status;
	private int conreport_no;
	private int conapproval_no;

	@Override
	public String toString() {
		return "ConsultApprovalUpdateStatusDTO [member_no=" + member_no + ", status=" + status + ", conreport_no="
				+ conreport_no + ", conapproval_no=" + conapproval_no + "]";
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

	public int getConapproval_no() {
		return conapproval_no;
	}

	public void setConapproval_no(int conapproval_no) {
		this.conapproval_no = conapproval_no;
	}

}
