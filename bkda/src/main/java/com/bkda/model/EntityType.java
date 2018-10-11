package com.bkda.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="entitytypes")
public class EntityType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="typeid")
	protected long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "propertytypes")
	protected Set<PropertyType> properties;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PropertyType> getProperties() {
		return properties;
	}

	public void setProperties(Set<PropertyType> properties) {
		this.properties = properties;
	}
	
}
