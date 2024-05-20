package mateourrutia.domain;

import mateourrutia.FileWriter.ObjectWriter;

import java.io.Serializable;

public class Carrera implements Serializable, ObjectWriter {

	private Integer id;
	private String name;

	public Carrera() {}

	public Carrera(
			Integer id,
			String name
	) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return "Carrera{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
