package mateourrutia.Service;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Listed;
import mateourrutia.utils.Logger;

import java.util.UUID;

/**
 * Es un helper para el ClientService, maneja todo lo que tenga que ver
 * con cuentas. Mantiene sincronizado un array interno de tipo Listed
 * con aquel almacenado en el archivo.
 */
public class AccountService implements AccountDAO {
	private final 	AccountDAO 			accountDAO;
	private final 	ClientService		clientService;
	private 		Listed<Account> 	accounts;

	public AccountService(
			AccountDAO 		accountDAO,
			ClientService	clientService
	) {
		this.accountDAO 	= accountDAO;
		this.clientService 	= clientService;

		reload();
	}

	public void reload() {
		accounts = accountDAO.getAll();
		Logger.log(Logger.LogLevel.SUCCESS, "Accounts data loaded successfully!");
		System.out.println("Loading Account Data done!");
	}

	@Override
	public void create(Account account, Client client) throws ObjectNotFoundException, OperationFailedException {
		client.getAccounts().create(account);
		clientService.update(client);
		accountDAO.create(account, client);
		accounts.create(account);
	}

	@Override
	public void add(Account account, Client client) throws ObjectNotFoundException, ObjectAlreadyExistsException, OperationFailedException {
		accountDAO.add(account, client);
		accounts.add(account);
		client.getAccounts().add(account);
	}

	@Override
	public Account get(UUID uuid, Client client) throws ObjectNotFoundException {
		return accounts.get(uuid);
	}

	@Override
	public Account get(UUID uuid) throws ObjectNotFoundException {
		return accounts.get(uuid);
	}

	@Override
	public Account get(Long CBU) throws ObjectNotFoundException {
		return accountDAO.get(CBU);
	}

	@Override
	public Client getClient(UUID uuid) throws ObjectNotFoundException {
		return accountDAO.getClient(uuid);
	}

	@Override
	public boolean update(Account account, Client client) throws ObjectNotFoundException, OperationFailedException {
		client.getAccounts().set(account);
		accounts.set(account);

		return accountDAO.update(account, client);
	}

	@Override
	public boolean update(Account account) throws ObjectNotFoundException, OperationFailedException {
		Client client = accountDAO.getClient(account.getUuid());
		client.getAccounts().set(account);

		accounts.set(account);
		return accountDAO.update(account);
	}

	@Override
	public boolean delete(UUID uuid, Client client) throws ObjectNotFoundException, OperationFailedException {
		client.getAccounts().remove(uuid);
		accounts.remove(uuid);

		return accountDAO.delete(uuid, client);
	}

	@Override
	public Listed<Account> getAll() {
		return accounts;
	}
}
