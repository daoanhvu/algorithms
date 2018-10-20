package com.bkda.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users_groups")
public class UserGroup {
	public enum UserGroupRole {
		MEMBER,
		ADMIN,
		OWNER;
		
		public static String nameOf(UserGroupRole role) {
			switch(role) {
				case MEMBER: return "Member";
				case ADMIN: return "Admin";
				case OWNER: return "Owner";
				default:
					return "UNDEFINED";
			}
		}
	}
	
	@EmbeddedId
	private UserGroupKey id = new UserGroupKey();
	
	@JsonIgnoreProperties(value = {"properties", "email", "firstName", "lastName", "groups", "scopes", "avatar", "password", "startDate", "status"})
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "user_objectid")
	@MapsId("userId")
	private User user;
	
	@JsonIgnoreProperties(value = {"propeties", "owner", "members"})
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "group_objectid")
	@MapsId("groupId")
	private Group group;
	
	@Enumerated
	@Column(columnDefinition = "smallint")
	private UserGroupRole role; 
	
	private Date joinTime;

	public UserGroupKey getId() {
		return id;
	}

	public void setId(UserGroupKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public UserGroupRole getRole() {
		return role;
	}

	public void setRole(UserGroupRole role) {
		this.role = role;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
}
