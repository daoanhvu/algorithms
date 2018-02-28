package com.bkda.entity;

import java.lang.reflect.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="propertytypes")
public class PropertyType {
	
	@Id
	@Column(name="name")
	private String name;
	
//	@Column(name="type")
//	private Type type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Type getType() {
//		return type;
//	}
//
//	public void setType(Type type) {
//		this.type = type;
//	}
	
}
