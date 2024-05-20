package mateourrutia.DAO.interfaces;

import java.util.List;

import mateourrutia.domain.Pabellon;

public interface PabellonDAO {

	void create(Pabellon pabellon);

	Pabellon get(int id);

	void update(Pabellon pabellon);

	boolean delete(int id);

	List<Pabellon> getAll();

}
