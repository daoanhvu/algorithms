package com.bkda.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScopeKey implements Serializable {
	
//	@Column(name="user_objectid")
//	private long userId;
	private String application;
	@Column(name = "_group")
	private String group;
	private String role;
	
//	public long getUserId() {
//		return userId;
//	}
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
