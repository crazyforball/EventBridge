package com.emsrepo.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uid")
	private Integer uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="utype")
	private Integer utype;

	@Column(name="gender")
	private int gender;
	
	@Column(name="email")
	private String email;
	
	@Column(name="DOB")
	private Date DOB;
	
	@Column(name="phoneNum")
	private String phoneNum;
	
	@Column(name="passport")
	private String passport;
	
	@Column(name="driverLicense")
	private String driverLicense;

	@Column(name="status")
	private String status;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy="creator")
	@JsonIgnore
	private Set<Event> createdEvents = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(name="t_booking",
			joinColumns={@JoinColumn(name="uid", referencedColumnName="uid")},
			inverseJoinColumns={@JoinColumn(name="eid", referencedColumnName="eid")})
	private Set<Event> bookingEvents;
	
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

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
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

	public Set<Event> getBookingEvents() {
		return bookingEvents;
	}

	public void setBookingEvents(Set<Event> bookingEvents) {
		this.bookingEvents = bookingEvents;
	}
	
	@Override
	public String toString() {
		return "uid:" + uid + " username:" + username + " utype:" + utype + " email:" + email + " phoneNum:" + phoneNum;
	}
}
