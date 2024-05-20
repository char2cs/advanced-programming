package mateourrutia.domain;

public class Administrativo extends Persona {
	private String departamento;

	public Administrativo() {}

	public Administrativo(
			String departamento
	) {
		this.departamento = departamento;
	}

	public Administrativo(
			Integer id,
			Integer dni,
			String nombre,
			String apellido,
			String departamento
	) {
		super(id, dni, nombre, apellido);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Administrativo{" +
				"departamento='" + departamento + '\'' +
				'}';
	}
}
