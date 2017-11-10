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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.emsrepo.utils.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_event")
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7152751631677552063L;

	public Event() {
		super();
		this.status = "APPROVED";
		this.createDate = DateTimeUtil.getNowadayTime();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eid")
	private int eid;

	@Column(name = "eventname", nullable = false)
	private String eventName;

	@Column(name = "description")
	private String description;

	@Column(name = "location")
	private String location;

	@Column(name = "startdate")
	private String startDate;

	@Column(name = "enddate")
	private String endDate;

	@Column(name = "status")
	private String status;

	@Column(name = "capacity")
	private int capacity;

	@Column(name = "fees")
	private double fees;

	@Column(name = "imageUrl")
	private String imageUrl;

	@Column(name = "audioUrl")
	private String audioUrl;

	@Column(name = "category")
	private String category;

	@Column(name = "createdate")
	private String createDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid")
	private User creator;

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "event")
	@JsonIgnore
	private Set<Booking> relatedBookings = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "event")
	@JsonIgnore
	private Set<Following> relatedFollowings = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "event")
	@JsonIgnore
	private Set<Comment> relatedComments = new HashSet<>();

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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<Booking> getRelatedBookings() {
		return relatedBookings;
	}

	public void setRelatedBookings(Set<Booking> relatedBookings) {
		this.relatedBookings = relatedBookings;
	}

	public Set<Following> getRelatedFollowings() {
		return relatedFollowings;
	}

	public void setRelatedFollowings(Set<Following> relatedFollowings) {
		this.relatedFollowings = relatedFollowings;
	}

	public Set<Comment> getRelatedComments() {
		return relatedComments;
	}

	public void setRelatedComments(Set<Comment> relatedComments) {
		this.relatedComments = relatedComments;
	}

	@Override
	public String toString() {
		return "Event [eid=" + eid + ", eventName=" + eventName + ", description=" + description + ", location="
				+ location + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", capacity="
				+ capacity + ", fees=" + fees + ", imageUrl=" + imageUrl + ", audioUrl=" + audioUrl + ", category="
				+ category + "]";
	}

}
