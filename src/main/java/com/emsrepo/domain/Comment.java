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
@Table(name = "t_comment", uniqueConstraints = { @UniqueConstraint(columnNames = { "uid", "eid" }) })
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226610390734699298L;

	public Comment() {
		super();
		this.commentDate = DateTimeUtil.getNowadayTime();
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private Integer cid;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid")
	private User creator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eid")
	private Event event;

	@Column(name = "commentdate")
	private String commentDate;
	
	@Column(name = "rating")
	private int rating;

	@Column(name = "content")
	private String content;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
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

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "cid:" + cid + " uid:" + creator.getUid() + " eid:" + event.getEid() + " commentDate:" + commentDate;
	}
}
