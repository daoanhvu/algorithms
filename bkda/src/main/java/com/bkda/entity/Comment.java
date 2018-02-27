package com.bkda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	//yhyhyhy
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="commentid")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	//YYYY-MM-DD HH:mm:ss
	@Column(name="_datetime", length = 19)
	private String dateTime;
	
	@Column(name="content")
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
