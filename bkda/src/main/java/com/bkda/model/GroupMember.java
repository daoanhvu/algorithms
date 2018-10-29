package com.bkda.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * This model is not an entity that maps to a table, we use this for representation
 * of a group's member, use native query to fetch data into this model
 * @author davu
 */
public class GroupMember implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2671147423672053582L;
	
	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private String role;
	private Date joinedDate;
	
	public GroupMember() { }
	
	public GroupMember(long id, 
			String usrname, 
			String fn, String ln, int roleIdx, Date jd) {
		this.id = id;
		this.username = usrname;
		this.firstName = fn;
		this.lastName = ln;
		this.role = UserGroup.UserGroupRole.nameOf(UserGroup.UserGroupRole.values()[roleIdx]);
		this.joinedDate = jd;
	}
	
	public GroupMember(Object ...fields) {
		this.id = ((BigInteger)fields[0]).longValue();
		this.username = (String)fields[1];
		this.firstName = (String)fields[2];
		this.lastName = (String)fields[3];
		int roleIdx = (Short)fields[4];
		this.role = UserGroup.UserGroupRole.nameOf(UserGroup.UserGroupRole.values()[roleIdx]);
		this.joinedDate = (Date)fields[5];
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
}
