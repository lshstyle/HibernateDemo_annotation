package com.hibernate.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="student")
@TableGenerator(
		name="Student_GEN",
		table="generator_table",
		pkColumnName="pk_key",
		valueColumnName="pk_value",
		pkColumnValue="student",
		allocationSize=1
		)
public class Student {
	private int id;
	private String name;
	private int age;
	private Date birthdate;
	private Sex sex;
	
	@Enumerated(EnumType.ORDINAL)
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	@Temporal(TemporalType.DATE)
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="Student_GEN")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
