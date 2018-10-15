package com.bkda.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Scope {
	
	@EmbeddedId
	private ScopeKey id;
	
	@MapsId("userid")
	@ManyToOne
	private User user;
	
	public Scope() {
	}
	
	public Scope(User user, String application, String group, String role) {
		this.id = new ScopeKey();
		this.setUser(user);
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
