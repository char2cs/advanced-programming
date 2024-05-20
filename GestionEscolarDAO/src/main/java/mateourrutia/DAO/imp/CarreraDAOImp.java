package mateourrutia.DAO.imp;

import mateourrutia.DAO.interfaces.CRUD;
import mateourrutia.FileWriter.FileWriter;
import mateourrutia.domain.Alumno;
import mateourrutia.domain.Carrera;

import java.util.Collections;
import java.util.List;

public class CarreraDAOImp implements CRUD<Carrera> {
	private final FileWriter<Carrera> fileWriter = new FileWriter<>( Carrera.class.getSimpleName() );

	@Override
	public void create(Carrera carrera) {
		fileWriter.create( carrera );
	}

	@Override
	public void add(Carrera carrera) {
		fileWriter.add( carrera );
	}

	@Override
	public Carrera get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Carrera carrera) {
		return fileWriter.update( carrera );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Carrera> getAll() {
		return fileWriter.getAll();
	}

}
