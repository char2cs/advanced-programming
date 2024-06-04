package mateourrutia.Service;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.CRUD;
import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Listed;
import mateourrutia.utils.Logger;

import java.util.List;
import java.util.UUID;

/**
 * Client Service es el encargado de cargar los datos del cliente
 * en runtime. A su vez, es la fuente principal donde se consultan
 * los datos del cliente.
 *
 * Sin la utilizacion del Listener, mantiene sincronizado
 * la lista interna de clientes, junto a las cuentas, con
 * los cambios que sucedan al archivo fisico de cliente.
 */
public class ClientService implements CRUD<Client> {
	private final 	ClientDAO 		clientDAO;
	private final 	AccountService 	accountService;
	private 		Listed<Client> 	clients = new Listed<>();

	public ClientService(
			ClientDAO clientDAO,
			AccountDAO accountDAO
	) {
		this.clientDAO 		= clientDAO;
		this.accountService = new AccountService(accountDAO, this);
	}

	/**
	 * Devuelve un thread que solo leera los datos una primera vez,
	 * sirve para esperar a que cargen los datos para continuar con la
	 * aplicacion.
	 * @return
	 */
	public Thread coldStart() {
		Thread thread = new Thread( new FileListener(
				1000,
				getFilePath(),
				true,
				this::reload
		) );

		thread.start();

		try {
			thread.join(); // Esperamos a que cargen los datos.
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return thread;
	}

	public Thread listener() {
		Thread thread = new Thread( new FileListener(
				1000,
				getFilePath(),
				this::reload
		) );
		thread.start();

		return thread;
	}

	public void reload() {
		clients = new Listed<>( clientDAO.getAll() );
		Logger.log(Logger.LogLevel.SUCCESS, "Client data loaded successfully!");
		System.out.println("Loading Client Data done!");
	}

	public AccountService getAccountService() {
		return accountService;
	}

	@Override
	public String getFilePath() {
		return clientDAO.getFilePath();
	}

	@Override
	public void create(Client client) {
		clientDAO.create(client);
		clients.create(client);
	}

	@Override
	public void add(Client client) throws ObjectAlreadyExistsException {
		clientDAO.add(client);
		clients.add(client);
	}

	@Override
	public Client get(UUID uuid) throws ObjectNotFoundException {
		return clients.get( uuid );
	}

	@Override
	public boolean update(Client client) throws ObjectNotFoundException, OperationFailedException {
		clients.set(client);
		return clientDAO.update(client);
	}

	@Override
	public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
		clients.remove( uuid );
		return clientDAO.delete( uuid );
	}

	@Override
	public List<Client> getAll() {
		return clients.getList();
	}
}
