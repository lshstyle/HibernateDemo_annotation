package com.hibernate.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Org {
	private int id;
	private String name;
	private Org parent;
	private Set<Org> child = new HashSet<Org>();
	
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
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Org getParent() {
		return parent;
	}
	public void setParent(Org parent) {
		this.parent = parent;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
	public Set<Org> getChild() {
		return child;
	}
	public void setChild(Set<Org> child) {
		this.child = child;
	}
	
	
}
