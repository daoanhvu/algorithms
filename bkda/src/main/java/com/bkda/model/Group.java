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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
@Inheritance( strategy = InheritanceType.JOINED )
public class Group extends GenericObject {
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@OneToMany(mappedBy = "group", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<UserGroup> members = new HashSet<>();

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
}
