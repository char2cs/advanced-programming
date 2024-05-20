package mateourrutia.DAO.imp;

import mateourrutia.FileWriter.FileWriter;
import mateourrutia.DAO.interfaces.CRUD;
import mateourrutia.domain.Mantenimiento;

import java.util.List;

public class MantenimientoDAOImp implements CRUD<Mantenimiento> {
	private final FileWriter<Mantenimiento> fileWriter = new FileWriter<>( Mantenimiento.class.getSimpleName() );

	@Override
	public void create(Mantenimiento mantenimiento) {
		fileWriter.create( mantenimiento );
	}

	@Override
	public void add(Mantenimiento mantenimiento) {
		fileWriter.add( mantenimiento );
	}

	@Override
	public Mantenimiento get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Mantenimiento mantenimiento) {
		return fileWriter.update( mantenimiento );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Mantenimiento> getAll() {
		return fileWriter.getAll();
	}

}
