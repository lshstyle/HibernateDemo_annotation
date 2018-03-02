package com.hibernate.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TeacherPk implements Serializable{
	private int id;
	private String name;
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
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TeacherPk) {
			TeacherPk pk = (TeacherPk)o;
			if (this.getId() == pk.getId() && this.getName().equals(pk.getName())) {
				return true;
			}
		} 
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}
