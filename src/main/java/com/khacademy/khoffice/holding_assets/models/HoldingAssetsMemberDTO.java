package com.khacademy.khoffice.holding_assets.models;

public class HoldingAssetsMemberDTO {
	private String hAssets_no;
	private String h_name;
	private String m_name;
	private String member_no;
	private String position;
	private String thumb_path;
	private String email;

	public HoldingAssetsMemberDTO() {
	}

	public String gethAssets_no() {
		return hAssets_no;
	}

	public void sethAssets_no(String hAssets_no) {
		this.hAssets_no = hAssets_no;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
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
		return "HoldingAssetsMemberDTO [hAssets_no=" + hAssets_no + ", h_name=" + h_name + ", m_name=" + m_name
				+ ", member_no=" + member_no + ", position=" + position + ", thumb_path=" + thumb_path + ", email="
				+ email + "]";
	}


}
