package com.khacademy.khoffice.project.models;

public class ProjectMemberDTO {
	
	private int pmember_no;
	private int project_no;
	private int is_leader;
	private int task_no;
	private int[] member_nos;
	private int member_no;
	private String name;
	private String position;
	private String thumb_path;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getPmember_no() {
		return pmember_no;
	}
	public void setPmember_no(int pmember_no) {
		this.pmember_no = pmember_no;
	}
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
	public int getIs_leader() {
		return is_leader;
	}
	public void setIs_leader(int is_leader) {
		this.is_leader = is_leader;
	}
	public int getTask_no() {
		return task_no;
	}
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}
	public int[] getMember_nos() {
		return member_nos;
	}
	public void setMember_nos(int[] member_nos) {
		this.member_nos = member_nos;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	
	
	


	
//	public ProjectMemberDTO(int pmember_no, int project_no, String is_leader, int task_no, int[] member_no) {
//		this.pmember_no = pmember_no;
//		this.project_no = project_no;
//		this.is_leader = is_leader;
//		this.task_no = task_no;
//		this.member_no = member_no;
//	}
	
	
	
}
