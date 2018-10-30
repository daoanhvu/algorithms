package com.bkda.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post extends GenericObject {
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	//YYYY-MM-DD HH:mm:ss
	@Column(name="_datetime")
	private Date dateTime;
		
	@Column(name="content")
	private String content;
	
	/**
	 * This is a string that containts tags,
	 * each tag separated by commas ,
	 */
	private String tags;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
