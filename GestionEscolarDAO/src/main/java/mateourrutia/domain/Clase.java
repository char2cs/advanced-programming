package mateourrutia.domain;

import mateourrutia.FileWriter.ObjectWriter;

import java.io.Serializable;
import java.util.List;

public class Clase implements Serializable, ObjectWriter {
	private Integer id;
	private String name;
	private Pabellon pabellon;
	private Profesor profesor;
	private List<Alumno> alumnos;

	public Clase() {}

	public Clase(
			Integer id,
			String name,
			Pabellon pabellon,
			Profesor profesor,
			List<Alumno> alumnos
	) {
		this.id = id;
		this.name = name;
		this.pabellon = pabellon;
		this.profesor = profesor;
		this.alumnos = alumnos;
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

	public Pabellon getPabellon() {
		return pabellon;
	}

	public void setPabellon(Pabellon pabellon) {
		this.pabellon = pabellon;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		return "Clase{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pabellon=" + pabellon +
				", profesor=" + profesor +
				", alumnos=" + alumnos +
				'}';
	}
}
