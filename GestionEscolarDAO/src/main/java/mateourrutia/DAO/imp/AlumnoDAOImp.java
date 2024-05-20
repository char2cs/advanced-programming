package mateourrutia.DAO.imp;

import mateourrutia.DAO.interfaces.AlumnoDAO;
import mateourrutia.domain.Alumno;
import mateourrutia.FileWriter.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImp implements AlumnoDAO {
	private final FileWriter<Alumno> fileWriter = new FileWriter<>( Alumno.class.getSimpleName() );

	@Override
	public void create(Alumno alumno) {
		fileWriter.create( alumno );
	}

	@Override
	public void add(Alumno alumno) {
		fileWriter.add( alumno );
	}

	@Override
	public Alumno get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Alumno alumno) {
		return fileWriter.update( alumno );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Alumno> getAll() {
		return fileWriter.getAll();
	}

}
