package com.khacademy.khoffice.chat_message.models;

import java.sql.Date;

public class ChatMessageDTO {
	private int cmessageNo;
	private String thumbPath;
	private String position;
	private String name;
	private String message;
	private Date time;
	private int cwindowNo;
	private int memberNo;
	
	public int getCmessageNo() {
		return cmessageNo;
	}
	public void setCmessageNo(int cmessageNo) {
		this.cmessageNo = cmessageNo;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getCwindowNo() {
		return cwindowNo;
	}
	public void setCwindowNo(int cwindowNo) {
		this.cwindowNo = cwindowNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
}
