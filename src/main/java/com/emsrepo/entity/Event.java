package com.emsrepo.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_event")
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7152751631677552063L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="eid")
	private int eid;
	
	@Column(name="eventname")
	private String eventName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="location")
	private String location;
	
	@Column(name="startdate")
	private String startDate;
	
	@Column(name="enddate")
	private String endDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="capacity")
	private int capacity;
	
	@Column(name="fees")
	private double fees;
	
	@Column(name="vacancy")
	private int vacancy;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	@Column(name="audioUrl")
	private String audioUrl;
	
	@Column(name="category")
	private String category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="uid")
	private User creator;

	@JsonIgnore
	@ManyToMany(mappedBy="bookingEvents")
	private Set<User> bookingUsers;
	
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getBookingUsers() {
		return bookingUsers;
	}

	public void setBookingUsers(Set<User> bookingUsers) {
		this.bookingUsers = bookingUsers;
	}

	
	
	
}
