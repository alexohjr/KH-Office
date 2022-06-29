package com.khacademy.khoffice.task.models;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.khacademy.khoffice.member.models.MemberDTO;

public class TaskDTO {
	private int task_no;
	private String name;
	private String content;
	private Date deadline;
	private MultipartFile file;
	private String file_path;
	private String status;
	private int project_no;
	private int member_no;
	private List<MemberDTO> memberList;
	private int[] member_nos;
	
	public int getTask_no() {
		return task_no;
	}
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public int[] getMember_nos() {
		return member_nos;
	}
	public void setMember_nos(int[] member_nos) {
		this.member_nos = member_nos;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public List<MemberDTO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberDTO> memberList) {
		this.memberList = memberList;
	}
	
	
}
