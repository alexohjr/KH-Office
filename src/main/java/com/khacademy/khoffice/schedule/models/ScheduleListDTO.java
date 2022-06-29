package com.khacademy.khoffice.schedule.models;

import java.util.Date;

public class ScheduleListDTO {

	private String h_num;
	private String h_name;
	private Date h_start;
	private Date h_end;
	private String s_date;
	private String status;

	public ScheduleListDTO() {
	}

	public String getH_num() {
		return h_num;
	}

	public void setH_num(String h_num) {
		this.h_num = h_num;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public Date getH_start() {
		return h_start;
	}

	public void setH_start(Date h_start) {
		this.h_start = h_start;
	}

	public Date getH_end() {
		return h_end;
	}

	public void setH_end(Date h_end) {
		this.h_end = h_end;
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ScheduleListDTO [h_num=" + h_num + ", h_name=" + h_name + ", h_start=" + h_start + ", h_end=" + h_end
				+ ", s_date=" + s_date + ", status=" + status + "]";
	}

	
	
}
