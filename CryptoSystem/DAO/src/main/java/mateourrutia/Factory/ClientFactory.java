package mateourrutia.Factory;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Imp.FileWriter.ClientImp;

public class ClientFactory {
	public static ClientDAO getClientDAO(
			String type
	) {
		return new ClientImp();
	}
}
