package com.khacademy.khoffice.member.models;

public class PasswordDTO {
	private String beforePassword;
	private String newPassword;
	private String newPassword2;
	private int memberNo;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getBeforePassword() {
		return beforePassword;
	}
	public void setBeforePassword(String beforePassword) {
		this.beforePassword = beforePassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	
}
