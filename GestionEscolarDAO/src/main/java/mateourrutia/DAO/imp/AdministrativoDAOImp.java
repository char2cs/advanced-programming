package mateourrutia.DAO.imp;

import mateourrutia.DAO.interfaces.CRUD;
import mateourrutia.FileWriter.FileWriter;
import mateourrutia.domain.Administrativo;

import java.util.List;

public class AdministrativoDAOImp implements CRUD<Administrativo> {
	private final FileWriter<Administrativo> fileWriter = new FileWriter<>( Administrativo.class.getSimpleName() );

	@Override
	public void create(Administrativo administrativo) {
		fileWriter.create( administrativo );
	}

	@Override
	public void add(Administrativo administrativo) {
		fileWriter.add( administrativo );
	}

	@Override
	public Administrativo get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Administrativo administrativo) {
		return fileWriter.update( administrativo );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Administrativo> getAll() {
		return fileWriter.getAll();
	}

}
