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
@Table(name="property")
public class Property {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="propertyid")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="type")
	private PropertyType type;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="owner")
	private GenericObject owner;
	
	@Column(name="value")
	private String value;

	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenericObject getOwner() {
		return owner;
	}

	public void setOwner(GenericObject owner) {
		this.owner = owner;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
