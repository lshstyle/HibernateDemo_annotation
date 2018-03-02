package com.hibernate.demo.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

@Entity
public class Husband {
	private int id;
	private String name;
	private Wife wife;
	private HusbandFeature hf;
	
	@Embedded
	@AttributeOverrides(
			{@AttributeOverride(name="name", column = @Column(name="fname"))
			})
	public HusbandFeature getHf() {
		return hf;
	}
	public void setHf(HusbandFeature hf) {
		this.hf = hf;
	}
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
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="wife_id", referencedColumnName="id"),
		@JoinColumn(name="wife_name", referencedColumnName="name")
	})
	public Wife getWife() {
		return wife;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}
	
	
}
