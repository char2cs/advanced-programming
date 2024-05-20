package mateourrutia.DAO.interfaces;

import mateourrutia.FileWriter.ObjectWriter;

import java.util.List;

public interface CRUD<T> {

	void create(T objects);

	void add(T objects);

	T get(Integer id);

	boolean update(T objects);

	boolean delete(Integer id);

	List<T> getAll();

}
