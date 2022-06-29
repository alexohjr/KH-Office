package com.khacademy.khoffice.member.models;

import java.sql.Date;

public class MemberDTO {
	private int member_no;
	private String name;
	private int department_no;
	private String position;
	private String email;
	private String phone;
	private String tel;
	private String zipcode;
	private String main_address;
	private String detail_address;
	private Date hire_date;
	private String hire_type;
	private String emp_type;
	private Date birthday;
	private String gender;
	private String marriage; 
	private String disabled;
	private String password;
	private String thumb_path;
	private int total_leave;
	private int remainder_leave;
	private String is_bookmark; //테이블에 없는 즐겨찾기 확인용
	
	public int getTotal_leave() {
		return total_leave;
	}
	public void setTotal_leave(int total_leave) {
		this.total_leave = total_leave;
	}
	public int getRemainder_leave() {
		return remainder_leave;
	}
	public void setRemainder_leave(int remainder_leave) {
		this.remainder_leave = remainder_leave;
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
	public int getDepartment_no() {
		return department_no;
	}
	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMain_address() {
		return main_address;
	}
	public void setMain_address(String main_address) {
		this.main_address = main_address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public String getHire_type() {
		return hire_type;
	}
	public void setHire_type(String hire_type) {
		this.hire_type = hire_type;
	}
	public String getEmp_type() {
		return emp_type;
	}
	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getThumb_path() {
		return thumb_path;
	}
	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}
	public String getIs_bookmark() {
		return is_bookmark;
	}
	public void setIs_bookmark(String is_bookmark) {
		this.is_bookmark = is_bookmark;
	}
	
	


}
