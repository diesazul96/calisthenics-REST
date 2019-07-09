package com.glb.bootcamp.model;

public class Routine {

	private int id;
	private String name;
	private String creator;
	private String[] ejercicios;

	public Routine() {
	}

	public Routine(int id, String name, String creator, String[] ejercicios) {
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.ejercicios = ejercicios;
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

	public String[] getEjercicios() {
		return ejercicios;
	}

	public void setEjercicios(String[] ejercicios) {
		this.ejercicios = ejercicios;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
