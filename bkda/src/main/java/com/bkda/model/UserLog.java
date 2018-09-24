package com.bkda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userlogs")
public class UserLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userlogid")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@Column(name="_datetime", length = 16)
	private String dateTime;
	
	@Column(name="logtype")
	private short logType;
	
	@Column(name="description")
	private String description;

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

	public short getLogType() {
		return logType;
	}

	public void setLogType(short logType) {
		this.logType = logType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
