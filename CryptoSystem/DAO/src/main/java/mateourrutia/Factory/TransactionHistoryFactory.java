package mateourrutia.Factory;

import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Imp.FileWriter.TransactionHistoryImp;

public class TransactionHistoryFactory {
	public static TransactionHistoryDAO getTransactionHistoryDAO(
			String type
	) {
		return new TransactionHistoryImp();
	}
}
