package mateourrutia;

import mateourrutia.DAO.imp.ProfesorDAOImp;
import mateourrutia.domain.Profesor;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ProfesorDAOImp profesorDAO = new ProfesorDAOImp();

        profesorDAO.add(new Profesor(
                1,
                45518340,
                "Mateo",
                "Urrutia",
                "Informatica",
                new ArrayList<>()
        ));

        profesorDAO.add(new Profesor(
                2,
                45518342,
                "Martina",
                "Conti",
                "Disenio Grafico",
                new ArrayList<>()
        ));

        profesorDAO.add(new Profesor(
                3,
                45518345,
                "ewqwe",
                "qweewqqwe",
                "Informatica II",
                new ArrayList<>()
        ));

        for ( Profesor alumno : profesorDAO.getAll() )
            System.out.println(alumno);

        profesorDAO.delete(2);

        for ( Profesor alumno : profesorDAO.getAll() )
            System.out.println(alumno);
    }

}