package com.khacademy.khoffice.reserve_assets.models;

public class ReserveMyMemberDTO {

	private String m_name;
	private String h_name;
	private String hAssets_no;
	private String start_time;
	private String end_time;
	private String people;
	private String purpose;
	private String rAssets_no;
	private String member_no;
	private String position;
	private String thumb_path;
	private String email;

	public ReserveMyMemberDTO() {
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public String gethAssets_no() {
		return hAssets_no;
	}

	public void sethAssets_no(String hAssets_no) {
		this.hAssets_no = hAssets_no;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getrAssets_no() {
		return rAssets_no;
	}

	public void setrAssets_no(String rAssets_no) {
		this.rAssets_no = rAssets_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getThumb_path() {
		return thumb_path;
	}

	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ReserveMyMemberDTO [m_name=" + m_name + ", h_name=" + h_name + ", hAssets_no=" + hAssets_no
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", people=" + people + ", purpose="
				+ purpose + ", rAssets_no=" + rAssets_no + ", member_no=" + member_no + ", position=" + position
				+ ", thumb_path=" + thumb_path + ", email=" + email + "]";
	}

}
