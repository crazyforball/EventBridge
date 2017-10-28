package com.emsrepo.vo;

import java.io.Serializable;

public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3773822308555416595L;
	
	private int uid;
	private String username;
	private String utype;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String email;
	private String status;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "uid:" + uid + " username:" + username + " utype:" + utype + " email:" + email + " phoneNum:" + phoneNum;
	}
}
