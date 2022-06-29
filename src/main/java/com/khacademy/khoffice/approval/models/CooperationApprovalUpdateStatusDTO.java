package com.khacademy.khoffice.approval.models;

public class CooperationApprovalUpdateStatusDTO {

	private int member_no;
	private String status;
	private int cooreport_no;
	private int cooapproval_no;

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

	public int getCooapproval_no() {
		return cooapproval_no;
	}

	public void setCooapproval_no(int cooapproval_no) {
		this.cooapproval_no = cooapproval_no;
	}

}
