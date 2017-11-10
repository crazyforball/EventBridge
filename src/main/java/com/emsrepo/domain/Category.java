package com.emsrepo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5588668013392735432L;

	public Category() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private Integer cid;

	@Column(name = "categoryName", unique = true, nullable = false)
	private String categoryName;

	@Column(name = "cdesc")
	private String cdesc;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	@Override
	public String toString() {
		return "cid:" + cid + " categoryName:" + categoryName + " cdesc:" + cdesc;
	}
}
