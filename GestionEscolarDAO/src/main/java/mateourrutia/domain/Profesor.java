package mateourrutia.domain;

import java.util.List;

public class Profesor extends Persona {
	private String especialidad;
	private List<Clase> clases;

	public Profesor() {}

	public Profesor(
			String especialidad,
			List<Clase> clases
	) {
		this.especialidad = especialidad;
		this.clases = clases;
	}

	public Profesor(
			Integer id,
			Integer dni,
			String nombre,
			String apellido,
			String especialidad,
			List<Clase> clases
	) {
		super(id, dni, nombre, apellido);
		this.especialidad = especialidad;
		this.clases = clases;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	@Override
	public String toString() {
		return super.toString() + "Profesor{" +
				"especialidad='" + especialidad + '\'' +
				", clases=" + clases +
				'}';
	}
}
