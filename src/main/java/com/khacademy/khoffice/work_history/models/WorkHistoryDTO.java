package com.khacademy.khoffice.work_history.models;

import java.util.Date;

public class WorkHistoryDTO {

	private int work_history_no;
	private Date start_time;
	private Date end_time;
	private String work_time;
	private String start_ip;
	private String end_ip;
	private String memo;
	private String strStart;
	private String strEnd;
	private int member_no;
	private int db_year;
	private int db_month;
	private int db_date;
	private int db_hour;
	private int db_minute;
	private int db_second;
	private String todayHasStartWork;
	private String todayHasEndWork;

	public String getTodayHasStartWork() {
		return todayHasStartWork;
	}

	public void setTodayHasStartWork(String todayHasStartWork) {
		this.todayHasStartWork = todayHasStartWork;
	}

	public String getTodayHasEndWork() {
		return todayHasEndWork;
	}

	public void setTodayHasEndWork(String todayHasEndWork) {
		this.todayHasEndWork = todayHasEndWork;
	}

	public String getStrStart() {
		return strStart;
	}

	public void setStrStart(String strStart) {
		this.strStart = strStart;
	}

	public String getStrEnd() {
		return strEnd;
	}

	public void setStrEnd(String strEnd) {
		this.strEnd = strEnd;
	}

	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}

	public int getDb_hour() {
		return db_hour;
	}

	public void setDb_hour(int db_hour) {
		this.db_hour = db_hour;
	}

	public int getDb_minute() {
		return db_minute;
	}

	public void setDb_minute(int db_minute) {
		this.db_minute = db_minute;
	}

	public int getDb_second() {
		return db_second;
	}

	public void setDb_second(int db_second) {
		this.db_second = db_second;
	}

	public int getDb_year() {
		return db_year;
	}

	public void setDb_year(int db_year) {
		this.db_year = db_year;
	}

	public int getDb_month() {
		return db_month;
	}

	public void setDb_month(int db_month) {
		this.db_month = db_month;
	}

	public int getDb_date() {
		return db_date;
	}

	public void setDb_date(int db_date) {
		this.db_date = db_date;
	}

	public int getWork_history_no() {
		return work_history_no;
	}

	public void setWork_history_no(int work_history_no) {
		this.work_history_no = work_history_no;
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

	public String getStart_ip() {
		return start_ip;
	}

	public void setStart_ip(String start_ip) {
		this.start_ip = start_ip;
	}

	public String getEnd_ip() {
		return end_ip;
	}

	public void setEnd_ip(String end_ip) {
		this.end_ip = end_ip;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	@Override
	public String toString() {
		return "WorkHistoryDTO [work_history_no=" + work_history_no + ", start_time=" + start_time + ", end_time="
				+ end_time + ", work_time=" + work_time + ", start_ip=" + start_ip + ", end_ip=" + end_ip + ", memo="
				+ memo + ", strStart=" + strStart + ", strEnd=" + strEnd + ", member_no=" + member_no + ", db_year="
				+ db_year + ", db_month=" + db_month + ", db_date=" + db_date + ", db_hour=" + db_hour + ", db_minute="
				+ db_minute + ", db_second=" + db_second + ", todayHasStartWork=" + todayHasStartWork
				+ ", todayHasEndWork=" + todayHasEndWork + "]";
	}

}
