package com.bkda.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
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
	
	private User source;
	private User destination;
	private ActionType actionType;
	private Date createTime;
	
	public User getSource() {
		return source;
	}
	public void setSource(User source) {
		this.source = source;
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
	
}
