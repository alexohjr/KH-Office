package com.khacademy.khoffice.work_history.models;

import java.util.Date;

public class AdminWorkHistoryDTO {

	private int work_history_no;
	private String dname;
	private String position;
	private String mname;
	private String thumb_path;
	private Date start_time;
	private Date end_time;
	private String work_time;
	private String memo;
	private int department_no;
	private int member_no;
	private String startTime;
	private String endTime;
	private int day;

	@Override
	public String toString() {
		return "AdminWorkHistoryDTO [work_history_no=" + work_history_no + ", dname=" + dname + ", position=" + position
				+ ", mname=" + mname + ", thumb_path=" + thumb_path + ", start_time=" + start_time + ", end_time="
				+ end_time + ", memo=" + memo + ", department_no=" + department_no + ", member_no=" + member_no
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWork_history_no() {
		return work_history_no;
	}

	public void setWork_history_no(int work_history_no) {
		this.work_history_no = work_history_no;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getThumb_path() {
		return thumb_path;
	}

	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
