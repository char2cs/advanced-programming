package mateourrutia.DAO;

import mateourrutia.Exceptions.DeleteFailedException;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.UpdateFailedException;
import mateourrutia.Domain.Client;

import java.util.List;

public interface CRUDAccount<T> {
	/**
	 * Crea el objecto sin importar que haya otro UUID repetido
	 * @param objects
	 * @param client
	 */
	void create(T objects, Client client);

	/**
	 * Verifica que no exista otro UUID.
	 * @param objects
	 * @param client
	 */
	void add(T objects, Client client) throws ObjectAlreadyExistsException;

	/**
	 * Busca la account seleccionada
	 * @param uuid
	 * @param client ? NULL, al ser NULL, la busqueda sera global.
	 * @return state
	 * @throws ObjectNotFoundException El objecto no fue encontrado.
	 */
	T get(Integer uuid, Client client) throws ObjectNotFoundException;

	/**
	 * Busca el objecto, por UUID y lo actualiza.
	 * @param objects
	 * @param client
	 * @return state
	 */
	boolean update(T objects, Client client) throws ObjectNotFoundException, UpdateFailedException;

	/**
	 * Borra el objecto relacionado por la UUID deseada.
	 * @param uuid
	 * @param client ? NULL, al ser NULL, la busqueda sera global.
	 * @return
	 */
	boolean delete(Integer uuid, Client client) throws ObjectNotFoundException, DeleteFailedException;

	/**
	 * Retorna todos los objectos disponibles.
	 * @return
	 */
	List<T> getAll();
}
