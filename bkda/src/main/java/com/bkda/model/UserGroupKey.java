package com.bkda.model;

import javax.persistence.Embeddable;

@Embeddable
public class UserGroupKey {
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
