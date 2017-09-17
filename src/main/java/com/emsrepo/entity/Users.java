package com.emsrepo.entity;

import javax.persistence.*;

@Entity
@Table(name="t_user")
public class Users {

	public Users() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uid")
	private Integer uid;
	
	@Column(name="account")
	private String account;
	
	@Column(name="uname")
	private String uname;
	
	@Column(name="u_pwd")
	private String u_pwd;
	
	@Column(name="utype")
	private Integer utype;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public Integer getUtype() {
		return utype;
	}

	public void setUtype(Integer utype) {
		this.utype = utype;
	}
}
