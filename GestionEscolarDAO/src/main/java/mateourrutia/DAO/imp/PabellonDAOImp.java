package mateourrutia.DAO.imp;

import java.util.List;

import mateourrutia.FileWriter.FileWriter;
import mateourrutia.DAO.interfaces.PabellonDAO;
import mateourrutia.domain.Pabellon;

public class PabellonDAOImp implements PabellonDAO {
	private final FileWriter<Pabellon> fileWriter = new FileWriter<>( Pabellon.class.getSimpleName() );

	@Override
	public void create(Pabellon pabellon) {
		fileWriter.create( pabellon );
	}

	@Override
	public void add(Pabellon pabellon) {
		fileWriter.add( pabellon );
	}

	@Override
	public Pabellon get(Integer id) {
		return fileWriter.get( id );
	}

	@Override
	public boolean update(Pabellon pabellon) {
		return fileWriter.update( pabellon );
	}

	@Override
	public boolean delete(Integer id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Pabellon> getAll() { return fileWriter.getAll(); }
}
