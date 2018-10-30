package com.bkda.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="groups")
@Inheritance( strategy = InheritanceType.JOINED )
public class Group extends GenericObject {
	
	private String description;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@JsonIgnoreProperties(value = {"properties", "groups", "scopes", "avatar", "password", "startDate", "status"})
	@OneToOne(targetEntity = User.class)
	private User owner;
	
//	@JsonIgnoreProperties(value = {"id", "group"})
	@JsonIgnore
	@OneToMany(mappedBy = "group", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<UserGroup> members = new HashSet<>();

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Set<UserGroup> getMembers() {
		return members;
	}

	public void setMembers(Set<UserGroup> members) {
		this.members = members;
	}
	
	public void addMember(UserGroup userGroup) {
		this.members.add(userGroup);
		userGroup.setGroup(this);
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
