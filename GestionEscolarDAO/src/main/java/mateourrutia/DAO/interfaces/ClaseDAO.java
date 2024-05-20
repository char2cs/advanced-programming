package mateourrutia.DAO.interfaces;

import java.util.List;

import mateourrutia.domain.Clase;
import mateourrutia.domain.Alumno;
import mateourrutia.domain.Profesor;

public interface ClaseDAO {

	void create(Clase clase);

	Clase read(Integer id);

	void update(Clase clase);

	void delete(Integer id);

	List<Clase> getAll();

	// ? Alumno && Profesores stuff...

	List<Alumno> getAlumnos(Integer claseId);

	List<Profesor> getProfesores(Integer claseId);

	void addAlumno(Integer alumnoId, Integer claseId);

	void addProfesor(Integer profesorId, Integer claseId);

	List<Clase> getClasesByAlumnoId(Integer alumnoId);

	List<Clase> getClasesByProfesorId(Integer profesorId);

}
