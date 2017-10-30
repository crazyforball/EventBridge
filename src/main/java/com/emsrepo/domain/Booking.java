package com.emsrepo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.emsrepo.utils.DateTimeUtil;

@Entity
@Table(name = "t_booking")
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

	@Column(name = "uid")
	private Integer uid;

	@Column(name = "eid")
	private Integer eid;

	// @Temporal(TemporalType.TIMESTAMP)
	// @Column(name = "bookingDate")
	// private Date bookingDate;

	private String bookingDate;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	// public Date getBookingDate() {
	// return bookingDate;
	// }
	//
	// public void setBookingDate(Date bookingDate) {
	// this.bookingDate = bookingDate;
	// }

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "bid:" + bid + " uid:" + uid + " eid:" + eid + " bookingDate:" + bookingDate;
	}
}
