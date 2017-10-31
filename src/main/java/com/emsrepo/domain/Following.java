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
@Table(name = "t_following", uniqueConstraints = { @UniqueConstraint(columnNames = { "uid", "eid" }) })
public class Following implements Serializable {

	private static final long serialVersionUID = -7809946402910519462L;

	public Following() {
		super();
		this.followingDate = DateTimeUtil.getNowadayTime();
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fid")
	private Integer fid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid")
	private User creator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eid")
	private Event event;

	@Column(name = "followingdate")
	private String followingDate;

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
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

	public String getFollowingDate() {
		return followingDate;
	}

	public void setFollowingDate(String followingDate) {
		this.followingDate = followingDate;
	}

	@Override
	public String toString() {
		return "fid:" + fid + " uid:" + creator.getUid() + " eid:" + event.getEid() + " followingDate:" + followingDate;
	}
}
