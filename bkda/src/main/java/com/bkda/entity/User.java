package com.bkda.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Inheritance( strategy = InheritanceType.JOINED )
public class User extends GenericObject {
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="email")
	private String email;
	
	//YYYY-MM-DD HH:mm:ss
	@Column(name="startdate", length = 19)
	private String startDate;
	
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@Column(name="sex")
	private char sex;
	
	@OneToOne
	@JoinColumn(name="avatar")
	private Media avatar;
	
	@Column(name="status")
	private int status;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "usernetworks",
			joinColumns = { @JoinColumn(name = "owner" ) },
			inverseJoinColumns = { @JoinColumn(name = "member") }
			)
	private Set<User> members = new HashSet<>();
	
	public Set<User> getMembers() {
		return members;
	}
	public void setMembers(Set<User> members) {
		this.members = members;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
