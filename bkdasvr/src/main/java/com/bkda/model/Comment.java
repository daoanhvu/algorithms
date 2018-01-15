package com.bkda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 104L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="commentid")
	private long id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="createddate")
	private Date createdDate;
	
	public Comment() {
		this.id = 0L;
	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
