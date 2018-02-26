package com.bkda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prodid")
	private int id;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="company")
	private Company company;
	
	@Column(name="price")
	private int price;
	
	@ManyToOne
	@JoinColumn(name="category")
	private ProductCategory category;
	
	@Column(name="instock")
	private int numberOfInstock;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumberOfInstock() {
		return numberOfInstock;
	}
	public void setNumberOfInstock(int numberOfInstock) {
		this.numberOfInstock = numberOfInstock;
	}
}
