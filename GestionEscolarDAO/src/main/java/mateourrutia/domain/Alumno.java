package mateourrutia.domain;

import java.util.List;

public class Alumno extends Persona {

	private Carrera carrera;
	private List<Clase> clases;

	public Alumno() {}

	public Alumno(
			Carrera carrera,
			List<Clase> clases
	) {
		this.carrera = carrera;
		this.clases = clases;
	}

	public Alumno(
			Integer id,
			Integer dni,
			String nombre,
			String apellido,
			Carrera carrera,
			List<Clase> clases
	) {
		super(id, dni, nombre, apellido);
		this.carrera = carrera;
		this.clases = clases;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	@Override
	public String toString() {
		return super.toString() + "Alumno{" +
				"carrera=" + carrera +
				", clases=" + clases +
				'}';
	}
}
