package com.bkda.dto;


import java.io.Serializable;

import com.bkda.model.User;

public class SignupDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -19701603628023107L;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String phoneNumber;
	private Character sex;
	
	public SignupDTO() {
		
	}
	
	public SignupDTO( User user ) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUsername();
		this.phoneNumber = user.getPhoneNumber();
		this.sex = user.getSex();
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pwd) {
		this.password = pwd;
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
