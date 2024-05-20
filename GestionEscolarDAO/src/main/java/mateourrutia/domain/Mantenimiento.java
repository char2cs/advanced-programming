package mateourrutia.domain;

public class Mantenimiento extends Persona {
	private String turno;

	public Mantenimiento() {}

	public Mantenimiento(
			String turno
	) {
		this.turno = turno;
	}

	public Mantenimiento(
			Integer id,
			Integer dni,
			String nombre,
			String apellido,
			String turno
	) {
		super(id, dni, nombre, apellido);
		this.turno = turno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Mantenimiento{" +
				"turno='" + turno + '\'' +
				'}';
	}
}
