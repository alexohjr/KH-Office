package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class ConsultReportAddFormDTO {

	private String mname; // 기안자
	private String dname; // 소속
	private String position; // 직위
	private Date reg_date; // 작성일
	private int conreport_no; // 문서번호(conreport_no)
	private String team_leader; // 팀장
	private String depart_leader; // 부서장
	private String ceo; // 대표이사
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

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getConreport_no() {
		return conreport_no;
	}

	public void setConreport_no(int conreport_no) {
		this.conreport_no = conreport_no;
	}

	public String getTeam_leader() {
		return team_leader;
	}

	public void setTeam_leader(String team_leader) {
		this.team_leader = team_leader;
	}

	public String getDepart_leader() {
		return depart_leader;
	}

	public void setDepart_leader(String depart_leader) {
		this.depart_leader = depart_leader;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
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

}
