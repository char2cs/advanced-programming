package mateourrutia.domain;

import mateourrutia.FileWriter.ObjectWriter;

import java.io.Serializable;

public abstract class Persona implements Serializable, ObjectWriter {

	private Integer id;
	private Integer dni;
	private String nombre;
	private String apellido;

	public Persona() {}

	public Persona(
			Integer id,
			Integer dni,
			String nombre,
			String apellido
	) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Persona{" +
				"id=" + id +
				", dni=" + dni +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				'}';
	}
}
