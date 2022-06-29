package com.khacademy.khoffice.chat_window.models;

import java.sql.Date;

public class ChatWindowDTO {
	private int cwindowNo;
	private int departmentNo;
	private String dName;
	private int memberNo;
	private String thumbPath;
	private String position;
	private String mName;
	private int cmessageNo;
	private String message;
	private Date time;
	private int chatMemberCount;
	
	public int getCwindowNo() {
		return cwindowNo;
	}
	public void setCwindowNo(int cwindowNo) {
		this.cwindowNo = cwindowNo;
	}
	public int getDepartmentNo() {
		return departmentNo;
	}
	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getCmessageNo() {
		return cmessageNo;
	}
	public void setCmessageNo(int cmessageNo) {
		this.cmessageNo = cmessageNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getChatMemberCount() {
		return chatMemberCount;
	}
	public void setChatMemberCount(int chatMemberCount) {
		this.chatMemberCount = chatMemberCount;
	}
	
}
