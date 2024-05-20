package mateourrutia.DAO.imp;

import java.util.List;
import java.util.ArrayList;

import mateourrutia.FileWriter.FileWriter;
import mateourrutia.DAO.interfaces.PabellonDAO;
import mateourrutia.domain.Pabellon;

public class PabellonDAOImp implements PabellonDAO {
	private final FileWriter<Pabellon> fileWriter = new FileWriter<>( Pabellon.class.getSimpleName() );

	@Override
	public void create(Pabellon pabellon) {
		fileWriter.create(pabellon);
	}

	@Override
	public Pabellon get(int id) {
		return fileWriter.get( id );
	}

	@Override
	public void update(Pabellon pabellon) {
		fileWriter.update( pabellon );
	}

	@Override
	public boolean delete(int id) {
		return fileWriter.delete( id );
	}

	@Override
	public List<Pabellon> getAll() {
		return fileWriter.getAll();
	}

}
