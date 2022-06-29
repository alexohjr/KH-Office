package com.khacademy.khoffice.holding_assets.models;

public class HoldingAssetsDTO {
	private String hAssets_no;
	private String name;
	private String member_no;

	public HoldingAssetsDTO() {
	}

	public String gethAssets_no() {
		return hAssets_no;
	}

	public void sethAssets_no(String hAssets_no) {
		this.hAssets_no = hAssets_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}


}
