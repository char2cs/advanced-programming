package mateourrutia.DAO;

import java.util.List;

import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.UpdateFailedException;
import mateourrutia.Exceptions.DeleteFailedException;

public interface CRUD<T> {
	/**
	 * Crea el objecto sin importar que haya otro UUID repetido
	 * @param objects
	 */
	void create(T objects);

	/**
	 * Verifica que no exista otro UUID.
	 * @param objects
	 */
	void add(T objects) throws ObjectAlreadyExistsException;

	/**
	 * Busca por UUID
	 * @param uuid
	 * @return
	 */
	T get(Integer uuid) throws ObjectNotFoundException;

	/**
	 * Busca el objecto, por UUID y lo actualiza.
	 * @param objects
	 * @return
	 */
	boolean update(T objects) throws ObjectNotFoundException, UpdateFailedException;

	/**
	 * Borra el objecto relacionado por la UUID deseada.
	 * @param uuid
	 * @return
	 */
	boolean delete(Integer uuid) throws ObjectNotFoundException, DeleteFailedException;

	/**
	 * Retorna todos los objectos disponibles.
	 * @return
	 */
	List<T> getAll();
}
