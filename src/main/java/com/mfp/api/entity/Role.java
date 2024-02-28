package com.mfp.api.entity;

import java.util.Set; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	@Id
	@Column(name = "id",unique = true,nullable = false)
	private int id;
	
	@Column(name = "name",unique = true,nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	

	
}
