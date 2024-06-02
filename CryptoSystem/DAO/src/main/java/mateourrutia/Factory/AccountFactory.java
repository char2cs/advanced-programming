package mateourrutia.Factory;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.Imp.FileWriter.AccountFile;
import mateourrutia.Imp.StringWriter.AccountString;

public class AccountFactory {
	public static AccountDAO getAccountDAO(
			PersistenceType type
	) {
		switch (type) {
			case FILEWRITER:
				return new AccountFile();

			case STRINGWRITER:
				return new AccountString();

			default:
				return null;
		}
	}
}
