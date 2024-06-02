package mateourrutia.Factory;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Imp.FileWriter.ClientFile;
import mateourrutia.Imp.StringWriter.ClientString;

public class ClientFactory {
	public static ClientDAO getClientDAO(
			PersistenceType type
	) {
		switch (type) {
			case FILEWRITER:
				return new ClientFile();

			case STRINGWRITER:
				return new ClientString();

			default:
				return null;
		}
	}
}
