package com.khacademy.khoffice.address.models;

public class AddressDTO {
	private int address_no;
	private int member_no;
	private String name;
	private String company;
	private String dept_position;
	private String email;
	private String phone;
	private int is_bookmark; 
	
	public int getAddress_no() {
		return address_no;
	}
	public void setAddress_no(int address_no) {
		this.address_no = address_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDept_position() {
		return dept_position;
	}
	public void setDept_position(String dept_position) {
		this.dept_position = dept_position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIs_bookmark() {
		return is_bookmark;
	}
	public void setIs_bookmark(int is_bookmark) {
		this.is_bookmark = is_bookmark;
	}
	
	
}
