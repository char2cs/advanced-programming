package mateourrutia.DAO;

import mateourrutia.Domain.Currency.AllCurrency;
import mateourrutia.Domain.Currency.CurrencyInterface;
import mateourrutia.Domain.TransactionHistory;

import java.util.List;

/**
 * La principal diferencia, es que obtenemos un metodo para
 * iterar sobre la lista usando el API Stream, seteando
 * algunos filtros.
 */
public interface TransactionHistoryDAO extends CRUD<mateourrutia.Domain.TransactionHistory> {
	List<TransactionHistory> getAll(
			TransactionHistory.Status   status,
			TransactionHistory.Type     type,
			CurrencyInterface 			currency,
			Long                        cbu,
			Double                      minBalance,
			Double                      maxBalance
	);
}
