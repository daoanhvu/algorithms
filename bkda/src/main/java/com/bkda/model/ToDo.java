package com.bkda.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="todos")
@Inheritance( strategy = InheritanceType.JOINED )
public class ToDo extends GenericObject {
	public enum ActionType {
		INVITATION_TO_GROUP,
		REQUEST_JOIN_GROUP
	}
	
	public enum ToDoStatus {
		PENDING,
		CANCEL,
		DENIED,
		APPROVED
	}
	
	@Column(name = "source_userid")
	private long sourceUserId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User destination;
	
	private ActionType actionType;
	
	private Date createTime;
	private Date lastUpdatedTime;
	
	public long getSourceUserId() {
		return sourceUserId;
	}
	public void setSourceUserId(long source) {
		this.sourceUserId = source;
	}
	public User getDestination() {
		return destination;
	}
	public void setDestination(User destination) {
		this.destination = destination;
	}
	public ActionType getActionType() {
		return actionType;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
