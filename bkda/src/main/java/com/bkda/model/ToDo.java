package com.bkda.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="todos")
@Inheritance( strategy = InheritanceType.JOINED )
public class ToDo extends GenericObject {
	public enum ActionType {
		INVITATION_TO_GROUP,
		REQUEST_JOIN_GROUP,
		INVITATION_TO_EVENT
	}
	
	public enum ToDoStatus {
		PENDING,
		CANCEL,
		DENIED,
		APPROVED
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_userid")
	private User sourceUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groupid")
	private Group group;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dest_userid")
	private User destination;
	
	@Enumerated
	@Column(name = "action_type", columnDefinition = "smallint")
	private ActionType actionType;
	
	@Column(name = "created_time")
	private Date createTime;
	
	@Column(name = "updated_time")
	private Date lastUpdatedTime;
	
	@Enumerated
	@Column(columnDefinition = "smallint")
	private ToDoStatus status;
	
	public User getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(User source) {
		this.sourceUser = source;
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
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
	public ToDoStatus getStatus() {
		return status;
	}
	public void setStatus(ToDoStatus status) {
		this.status = status;
	}
	
}
