package com.khacademy.khoffice.project.models;

import java.sql.Date;

public class ProjectListDTO {
	private int project_no;
	private String name;
	private String status;
	private int memberCount;
	private Date start_date;
	private Date end_date;
	private int taskCount;
	private String taskStatus;
	private String leaderName;
	private String leaderPosition;
	private String leaderThumb;
	private long progress;
	 
	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
	}

	public ProjectListDTO () {}
	
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderPosition() {
		return leaderPosition;
	}

	public void setLeaderPosition(String leaderPosition) {
		this.leaderPosition = leaderPosition;
	}

	public String getLeaderThumb() {
		return leaderThumb;
	}

	public void setLeaderThumb(String leaderThumb) {
		this.leaderThumb = leaderThumb;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	

}
