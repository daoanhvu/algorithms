package com.bkda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="genericobjects")
public class GenericObject {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="objectid")
	private long id;
	
	@Column(name="name")
	private String name;
}
