package com.hibernate.demo.model;

import javax.persistence.*;

@Entity
@Table(name="teacher")
public class Teacher {
	private String position;
	private int id;
	private String name;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(updatable=false)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
