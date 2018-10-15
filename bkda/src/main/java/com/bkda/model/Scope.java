package com.bkda.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Scope {
	
	@JsonIgnore
	@EmbeddedId
	private ScopeKey id;
	
//	@MapsId("userid")
//	@ManyToOne(cascade = CascadeType.ALL)
//	private User user;
	
	public Scope() {
	}
	
	public Scope(String application, String group, String role) {
		this.id = new ScopeKey();
//		this.setUser(user);
//		this.id.setUserId(user.getId());
		this.id.setApplication(application);
		this.id.setGroup(group);
		this.id.setRole(role);
	}

	public ScopeKey getId() {
		return id;
	}

	public void setId(ScopeKey id) {
		this.id = id;
	}
	
	public String getApplication() {
		return this.id.getApplication();
	}
	public void setApplication(String application) {
		this.id.setApplication(application);
	}
	public String getGroup() {
		return this.id.getGroup();
	}
	public void setGroup(String group) {
		this.id.setGroup(group);
	}
	public String getRole() {
		return this.id.getRole();
	}
	public void setRole(String role) {
		this.id.setRole(role);
	}
	
}
