package com.bkda.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "users_groups")
public class UserGroup {
	public enum UserGroupRole {
		MEMBER,
		ADMIN
	}
	
	@EmbeddedId
	private UserGroupKey id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("groupId")
	private Group group;
	
	@Enumerated
	@Column(columnDefinition = "smallint")
	private UserGroupRole role; 
	
	private Date joinTime;
}
