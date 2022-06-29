package com.khacademy.khoffice.approval.models;

import java.sql.Date;

public class VacationReportBoardListDTO {
	
	private int vacreport_no;
	private Date reg_date;
	private int member_no;
	private String type; // 휴가종류 (월차,연차,공가,병가)
	private String reason; // 휴가사유
	private Date start_date;
	private Date end_date;
	private String substitute; // 업무대리인 -- member_no로 가져오는건 같음.
	private String name;
	private String status;
	private String r;

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {

		if (status.equals("0")) {
			status = "대기중";
		} else if (status.equals("1")) {
			status = "진행중";
		} else if (status.equals("2")) {
			status = "반려";
		} else if (status.equals("3")) {
			status = "결재완료";
		} else {
			status = "들어온값이없습니다.";
		}

		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVacreport_no() {
		return vacreport_no;
	}

	public void setVacreport_no(int vacreport_no) {
		this.vacreport_no = vacreport_no;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getSubstitute() {
		return substitute;
	}

	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}
}
