package mateourrutia.DAO.imp;

import mateourrutia.FileWriter.FileWriter;
import mateourrutia.domain.Clase;
import mateourrutia.DAO.interfaces.CRUD;

import java.util.List;

public class ClaseDAOImp implements CRUD<Clase> {
	private final FileWriter<Clase> fileWriter = new FileWriter<>( Clase.class.getSimpleName() );

	@Override
	public void create(Clase clase) {
		fileWriter.create( clase );
	}

	@Override
	public void add(Clase clase) {
		fileWriter.add( clase );
	}

	@Override
	public Clase get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Clase clase) {
		return fileWriter.update( clase );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Clase> getAll() {
		return fileWriter.getAll();
	}

}
