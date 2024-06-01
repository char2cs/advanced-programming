package mateourrutia;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.SavingsAccount;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Factory.AccountFactory;
import mateourrutia.Factory.ClientFactory;

public class Main {
	public static void main(String[] args) {
		ClientDAO clientDAO = ClientFactory.getClientDAO("FileWriter");
		AccountDAO accountDAO = AccountFactory.getAccountDAO("FileWriter");

		Client mateo = new Client(
				20455183406L,
				"Mateo",
				"Urrutia",
				1128447029L,
				"mateo@rounds.com.ar",
				"Ituzaingo 670"
		);

		Client nothing = new Client(
				null,
				"",
				"",
				null,
				"",
				""
		);

		SavingsAccount savings = new SavingsAccount(mateo,
				23213
		);

		for ( Client client : clientDAO.getAll() )
			System.out.println(client);

		try {
			mateo.getAccounts().add(savings);
		}
		catch (ObjectAlreadyExistsException e) {
			throw new RuntimeException(e);
		}

		try {
			clientDAO.add(mateo);
			clientDAO.add(nothing);
		} catch (ObjectAlreadyExistsException e) {
			throw new RuntimeException(e);
		}

		for ( Client client : clientDAO.getAll() )
			System.out.println(client);
	}
}