package mateourrutia.DAO;

import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectNotFoundException;

import java.util.List;

public interface TransactionHistoryDAO extends CRUD<mateourrutia.Domain.TransactionHistory> {
	List<TransactionHistory> getAll(
			TransactionHistory.Status 	status,
			TransactionHistory.Type 	type,
			Long						cbu,
			double 						minBalance,
			double 						maxBalance
	);
}
