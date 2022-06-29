package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class VacationReportAddFormDTO {

	private String mname; // 작성자
	private String dname; // 소속(부서명)
	private String position; // 직위
	private Date reg_date; // 작성일
	private int vacreport_no; // 문서번호
	private String team_leader; // 팀장
	private String depart_leader; // 부서장
	private String ceo; // 대표이사
	private String substitute; // 업무대리인
	private String type; // 휴가 종류
	private Date start_date; // 휴가 시작일
	private Date end_date; // 휴가 끝나는일
	private String use_day; // 사용일수 (end_date - start_date로 계산)
	private String reason; // 휴가 사유
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

	public int getVacreport_no() {
		return vacreport_no;
	}

	public void setVacreport_no(int vacreport_no) {
		this.vacreport_no = vacreport_no;
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

	public String getSubstitute() {
		return substitute;
	}

	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getUse_day() {
		return use_day;
	}

	public void setUse_day(String use_day) {
		this.use_day = use_day;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
