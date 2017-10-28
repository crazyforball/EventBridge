package com.emsrepo.bean;

import java.io.Serializable;

public class UserToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1584287950157097490L;

	private String tokenId;
	
	private int uid;
	
	private String username;
	
	private String loginDate;
	
	private int userType;
	
	private String expiredTime;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

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

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

}
