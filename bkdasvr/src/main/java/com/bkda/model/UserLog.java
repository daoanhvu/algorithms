package com.bkda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

@Entity
@Table(name="userlogs")
public class UserLog implements Serializable {
	
	/** 
	 */
	private static final long serialVersionUID = 102L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="logid")
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid")
	private User user;
	
	@Column(name="createddate")
	private Date createdDate;
	
	@Column(name="haslocation")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean hasLocation;
	
	@Column(name="latitude")
	private float latitude;
	@Column(name="longitude")
	private float longitude;
	
	/**
	 * 0: Account created
	 * 1: Login
	 * 2: Logout
	 */
	@Column(name="actiontype")
	private int actionType;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isHasLocation() {
		return hasLocation;
	}

	public void setHasLocation(boolean hasLocation) {
		this.hasLocation = hasLocation;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	
}
