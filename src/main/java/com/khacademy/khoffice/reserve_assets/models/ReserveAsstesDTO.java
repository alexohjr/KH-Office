package com.khacademy.khoffice.reserve_assets.models;

import java.util.Date;
import java.util.List;

import com.khacademy.khoffice.member.models.MemberDTO;

public class ReserveAsstesDTO {
	private String rAssets_no;
	private String start;
	private String end;
	private String member_no;
	private String people;
	private String purpose;
	private String hAssets_no;
	private String name;
	private String reservationDate;
	private Date start_time;
	private Date end_time;
	

	public ReserveAsstesDTO() {
	}

	public String getReserve_no() {
		return rAssets_no;
	}

	public void setReserve_no(String rAssets_no) {
		this.rAssets_no = rAssets_no;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String gethAssets_no() {
		return hAssets_no;
	}

	public void sethAssets_no(String hAssets_no) {
		this.hAssets_no = hAssets_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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
	

	public String getrAssets_no() {
		return rAssets_no;
	}

	public void setrAssets_no(String rAssets_no) {
		this.rAssets_no = rAssets_no;
	}


	@Override
	public String toString() {
		return "ReserveAsstesDTO [reserve_no=" + rAssets_no + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", member_no=" + member_no + ", people=" + people + ", purpose=" + purpose + ", hassets_no="
				+ hAssets_no + ", name=" + name + ", reservationDate=" + reservationDate + "]";
	}

}
