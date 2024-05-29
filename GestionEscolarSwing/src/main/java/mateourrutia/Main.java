package mateourrutia;

import mateourrutia.DAO.imp.AlumnoDAOImp;
import mateourrutia.DAO.imp.ClaseDAOImp;
import mateourrutia.domain.*;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		ClaseDAOImp claseDAO = new ClaseDAOImp();

		claseDAO.add(new Clase(
				0,
				"Programacion Avanzada",
				new Pabellon(0, "Carcel 1", "USAL"),
				new Profesor(0, 45518340, "Mateo", "Urrutia", "Full stack developer", new ArrayList<>()),
				new ArrayList<>()
		));

		claseDAO.add(new Clase(
				1,
				"Analisis Matematico III",
				new Pabellon(0, "Carcel 1", "USAL"),
				new Profesor(0, 45518340, "Mateo", "Urrutia", "Full stack developer", new ArrayList<>()),
				new ArrayList<>()
		));

		claseDAO.add(new Clase(
				2,
				"Estructura de datos",
				new Pabellon(0, "Carcel 2", "USAL"),
				new Profesor(0, 45518340, "Mateo", "Urrutia", "Full stack developer", new ArrayList<>()),
				new ArrayList<>()
		));

		List<Clase> clases1 = claseDAO.getAll();
		clases1.remove(1);

		List<Clase> clases2 = claseDAO.getAll();
		clases2.remove(2);

		AlumnoDAOImp alumnoDAO = new AlumnoDAOImp();

		alumnoDAO.add(new Alumno(
				0,
				45518340,
				"Mateo",
				"Urrutia",
				new Carrera(0, "Ingenieria Informatica"),
				clases1
		));

		alumnoDAO.add(new Alumno(
				1,
				45518342,
				"Martina",
				"Conti",
				new Carrera(0, "Disenio Grafico"),
				clases2
		));

		for ( Alumno alumno : alumnoDAO.getAll() )
			System.out.println( alumno.toString() );
	}
}