package mateourrutia.DAO.imp;

import mateourrutia.FileWriter.FileWriter;
import mateourrutia.domain.Profesor;
import mateourrutia.DAO.interfaces.CRUD;

import java.util.List;

public class ProfesorDAOImp implements CRUD<Profesor> {
	private final FileWriter<Profesor> fileWriter = new FileWriter<>( Profesor.class.getSimpleName() );

	@Override
	public void create(Profesor profesor) {
		fileWriter.create( profesor );
	}

	@Override
	public void add(Profesor profesor) {
		fileWriter.add( profesor );
	}

	@Override
	public Profesor get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Profesor profesor) {
		return fileWriter.update( profesor );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Profesor> getAll() {
		return fileWriter.getAll();
	}

}
