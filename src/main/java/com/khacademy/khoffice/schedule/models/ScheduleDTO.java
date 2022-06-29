package com.khacademy.khoffice.schedule.models;

import java.util.Date;

public class ScheduleDTO {
	
	private String schedule_no;
	private String subject;
	private Date start_date;
	private Date end_date;
	private String member_no;
	
	public ScheduleDTO() {}
	
	
	public String getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(String schedule_no) {
		this.schedule_no = schedule_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}


	@Override
	public String toString() {
		return "ScheduleDTO [schedule_no=" + schedule_no + ", subject=" + subject + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", member_no=" + member_no + "]";
	}
	
	
	
	
	
}
