package com.bkda.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="genericobjects")
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class GenericObject {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="objectid")
	protected long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "objectid")
	protected Set<Property> properties;
	
	@ManyToOne
	@JoinColumn(name = "objecttypeid")
	protected EntityType objectType;
	
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

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	
}
