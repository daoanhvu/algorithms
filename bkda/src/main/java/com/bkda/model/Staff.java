package com.bkda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="staffs")
public class Staff extends User {
	
	//Format MM-DD-YYYY hh:mm:ss
	@Column(name="startworkingdate", length = 19)
	private String startWorkingDate;
	
	@ManyToOne
	@JoinColumn(name="company")
	private Company company;
	
	@Column(name="title")
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getStartWorkingDate() {
		return startWorkingDate;
	}
	public void setStartWorkingDate(String startDate) {
		this.startWorkingDate = startDate;
	}
}
