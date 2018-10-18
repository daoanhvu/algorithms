package com.bkda.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserGroupKey implements Serializable {
	private long userId;
	private long groupId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
}
