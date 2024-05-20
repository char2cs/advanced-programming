package mateourrutia.domain;

import mateourrutia.FileWriter.ObjectWriter;

import java.io.Serializable;

public class Pabellon implements Serializable, ObjectWriter {
	private Integer id;
	private String name;
	private String ubicacion;

	private static final long serialVersionUID = 1L;

	public Pabellon() {}

	public Pabellon(
			Integer id,
			String name,
			String ubicacion
	) {
		this.id = id;
		this.name = name;
		this.ubicacion = ubicacion;
	}

	@Override
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Pabellon{" +
				"id=" + id +
				", name='" + name + '\'' +
				", ubicacion='" + ubicacion + '\'' +
				'}';
	}
}
