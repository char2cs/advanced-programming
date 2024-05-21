package mateourrutia;

import mateourrutia.DAO.imp.ProfesorDAOImp;
import mateourrutia.domain.Profesor;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ProfesorDAOImp profesorDAO = new ProfesorDAOImp();

		profesorDAO.add(new Profesor(
				0,
				45518340,
				"Mateo",
				"Urrutia",
				"test",
				new ArrayList<>()
		));

		for (Profesor profesor : profesorDAO.getAll())
			System.out.println(profesor);
	}
}