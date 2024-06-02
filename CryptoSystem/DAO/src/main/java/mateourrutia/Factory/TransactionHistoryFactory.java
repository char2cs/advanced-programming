package mateourrutia.Factory;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Imp.FileWriter.TransactionHistoryFile;
import mateourrutia.Imp.StringWriter.TransactionHistoryString;

public class TransactionHistoryFactory {
	public static TransactionHistoryDAO getTransactionHistoryDAO(
			PersistenceType type
	) {
		switch (type) {
			case FILEWRITER:
				return new TransactionHistoryFile();

			case STRINGWRITER:
				return new TransactionHistoryString();

			default:
				return null;
		}
	}
}
