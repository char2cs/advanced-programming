package mateourrutia.DAO;

import mateourrutia.Domain.TransactionHistory;

import java.util.List;

/**
 * La principal diferencia, es que obtenemos un metodo para
 * iterar sobre la lista usando el API Stream, seteando
 * algunos filtros.
 */
public interface TransactionHistoryDAO extends CRUD<mateourrutia.Domain.TransactionHistory> {
	List<TransactionHistory> getAll(
			TransactionHistory.Status 	status,
			TransactionHistory.Type 	type,
			Long						cbu,
			double 						minBalance,
			double 						maxBalance
	);
}
