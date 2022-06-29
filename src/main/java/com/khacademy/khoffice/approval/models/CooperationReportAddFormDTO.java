package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class CooperationReportAddFormDTO {

	private String mname; // 요청자
	private Date reg_date; // 작성일
	private int cooreport_no; // 문서번호(cooreport_no)
	private String dname; // 요청자의 부서명
	private String request_team_leader; // 요청부서 직원
	private String cooperation_team_leader; // 협조부서 직원
	private int department_no; // 협조부서 이름
	private Date deadline; // 요청기한
	private String title; // 제목
	private String content; // 내용
	private int member_no;

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getCooreport_no() {
		return cooreport_no;
	}

	public void setCooreport_no(int cooreport_no) {
		this.cooreport_no = cooreport_no;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getRequest_team_leader() {
		return request_team_leader;
	}

	public void setRequest_team_leader(String request_team_leader) {
		this.request_team_leader = request_team_leader;
	}

	public String getCooperation_team_leader() {
		return cooperation_team_leader;
	}

	public void setCooperation_team_leader(String cooperation_team_leader) {
		this.cooperation_team_leader = cooperation_team_leader;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}

}
