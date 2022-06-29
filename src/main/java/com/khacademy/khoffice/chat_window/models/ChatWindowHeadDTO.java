package com.khacademy.khoffice.chat_window.models;

public class ChatWindowHeadDTO {
	private int cwindowNo;
	private String thumbPath;
	int cwindowMemberCount;
	String cwindowName;
	
	public int getCwindowNo() {
		return cwindowNo;
	}
	public void setCwindowNo(int cwindowNo) {
		this.cwindowNo = cwindowNo;
	}
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	public int getCwindowMemberCount() {
		return cwindowMemberCount;
	}
	public void setCwindowMemberCount(int cwindowMemberCount) {
		this.cwindowMemberCount = cwindowMemberCount;
	}
	public String getCwindowName() {
		return cwindowName;
	}
	public void setCwindowName(String cwindowName) {
		this.cwindowName = cwindowName;
	}
}
