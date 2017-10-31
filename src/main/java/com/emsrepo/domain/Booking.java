package com.emsrepo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.emsrepo.utils.DateTimeUtil;

@Entity
@Table(name = "t_booking", uniqueConstraints = { @UniqueConstraint(columnNames = { "uid", "eid" }) })
public class Booking implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226610390734699298L;

	public Booking() {
		super();
		this.bookingDate = DateTimeUtil.getNowadayTime();
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid")
	private Integer bid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid")
	private User creator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eid")
	private Event event;

	@Column(name = "bookingdate")
	private String bookingDate;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "bid:" + bid + " uid:" + creator.getUid() + " eid:" + event.getEid() + " bookingDate:" + bookingDate;
	}
}
