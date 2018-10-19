package com.bkda.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="users")
@Inheritance( strategy = InheritanceType.JOINED )
public class User extends GenericObject {
	
	public enum UserStatus {
		ACTIVE,
		INACTIVE,
		PANNED,
		DELETED
	}
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@JsonProperty(required = true)
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	//YYYY-MM-DD HH:mm:ss
	@Column(name="startdate")
	private Date startDate;
	
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@Column(name="sex")
	private char sex;
	
	@OneToOne
	@JoinColumn(name="avatar", nullable = true)
	private Media avatar;
	
	@Enumerated
	@Column(name="status", columnDefinition = "smallint")
	private UserStatus status;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy="user")
	private Set<UserGroup> groups = new HashSet<>();
	
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Scope> scopes = new HashSet<>();
	
	public Set<UserGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<UserGroup> grps) {
		this.groups = grps;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Media getAvatar() {
		return avatar;
	}
	public void setAvatar(Media avatar) {
		this.avatar = avatar;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public Set<Scope> getScopes() {
		return scopes;
	}
	public void setScopes(Set<Scope> scopes) {
		this.scopes = scopes;
	}
	
	public void addToGroup(UserGroup userGroup) {
		this.groups.add(userGroup);
	}
}
