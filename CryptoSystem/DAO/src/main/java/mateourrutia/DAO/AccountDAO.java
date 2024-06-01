package mateourrutia.DAO;

import mateourrutia.Domain.Account;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.Domain.Client;
import mateourrutia.utils.Listed;

import java.util.UUID;

public interface AccountDAO {
	/**
	 * Crea el objecto sin importar que haya otro UUID repetido
	 * @param objects
	 * @param client
	 */
	void create(Account objects, Client client) throws ObjectNotFoundException, OperationFailedException;

	/**
	 * Verifica que no exista otro UUID.
	 * @param objects
	 * @param client
	 */
	void add(Account objects, Client client) throws ObjectNotFoundException, ObjectAlreadyExistsException, OperationFailedException;

	/**
	 * Busca la account seleccionada
	 * @param uuid
	 * @param client
	 * @return state
	 * @throws ObjectNotFoundException El objecto no fue encontrado.
	 */
	Account get(UUID uuid, Client client) throws ObjectNotFoundException;

	/**
	 * Busca la account seleccionada global.
	 * !!! Siempre que se pueda, EVITAR METODOS DE BUSQUEDA GLOBAL
	 * @param uuid
	 * @return
	 * @throws ObjectNotFoundException
	 */
	Account get(UUID uuid) throws ObjectNotFoundException;

	/**
	 * Busca el objecto, por UUID y lo actualiza.
	 * @param objects
	 * @param client
	 * @return state
	 */
	boolean update(Account objects, Client client) throws ObjectNotFoundException, OperationFailedException;

	/**
	 * Busca el objecto, por UUID y lo actualiza.
	 * !!! Siempre que se pueda, EVITAR METODOS DE BUSQUEDA GLOBAL
	 * @param objects
	 * @return state
	 */
	boolean update(Account objects) throws ObjectNotFoundException, OperationFailedException;

	/**
	 * Borra el objecto relacionado por la UUID deseada.
	 * @param uuid
	 * @param client ? NULL, al ser NULL, la busqueda sera global.
	 * @return
	 */
	boolean delete(UUID uuid, Client client) throws ObjectNotFoundException, OperationFailedException;

	/**
	 * Retorna todos los objectos disponibles.
	 * !!! Siempre que se pueda, EVITAR METODOS DE BUSQUEDA GLOBAL
	 * @return
	 */
	Listed<Account> getAll();
}
