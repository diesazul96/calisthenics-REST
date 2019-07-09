package com.glb.bootcamp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String username;
	private String name;
	private int edad;
	private int[] routines;

	public User() {
	}

	public User(String username, String name, int edad, int[] routines) {
		this.username = username;
		this.name = name;
		this.edad = edad;
		this.routines = routines;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int[] getRoutines() {
		return routines;
	}

	public void setRoutines(int[] routines) {
		this.routines = routines;
	}
}
