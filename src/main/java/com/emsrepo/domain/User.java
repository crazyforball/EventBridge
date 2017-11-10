package com.emsrepo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {
		super();
		this.utype = 1;
		this.status = "PASS";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private Integer uid;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "utype", nullable = false)
	private Integer utype;

	@Column(name = "gender")
	private int gender;

	@Column(name = "email")
	private String email;

	@Column(name = "DOB")
	private String DOB;

	@Column(name = "phoneNum")
	private String phoneNum;

	@Column(name = "passport")
	private String passport;

	@Column(name = "driverLicense")
	private String driverLicense;

	@Column(name = "status", nullable = false)
	private String status;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "creator")
	@JsonIgnore
	private Set<Event> createdEvents = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "creator")
	@JsonIgnore
	private Set<Booking> createdBookings = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "creator")
	@JsonIgnore
	private Set<Following> createdFollowings = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "creator")
	@JsonIgnore
	private Set<Comment> createdComments = new HashSet<>();

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUtype() {
		return utype;
	}

	public void setUtype(Integer utype) {
		this.utype = utype;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Event> getCreatedEvents() {
		return createdEvents;
	}

	public void setCreatedEvents(Set<Event> createdEvents) {
		this.createdEvents = createdEvents;
	}

	public Set<Booking> getCreatedBookings() {
		return createdBookings;
	}

	public void setCreatedBookings(Set<Booking> createdBookings) {
		this.createdBookings = createdBookings;
	}

	public Set<Following> getCreatedFollowings() {
		return createdFollowings;
	}

	public void setCreatedFollowings(Set<Following> createdFollowings) {
		this.createdFollowings = createdFollowings;
	}

	public Set<Comment> getCreatedComments() {
		return createdComments;
	}

	public void setCreatedComments(Set<Comment> createdComments) {
		this.createdComments = createdComments;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", utype=" + utype + ", gender=" + gender + ", email=" + email + ", DOB="
				+ DOB + ", phoneNum=" + phoneNum + ", passport=" + passport + ", driverLicense=" + driverLicense
				+ ", status=" + status + "]";
	}
}
