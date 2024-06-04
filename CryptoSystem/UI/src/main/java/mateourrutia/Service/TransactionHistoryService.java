package mateourrutia.Service;

import mateourrutia.DAO.CRUD;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Listed;
import mateourrutia.utils.Logger;

import java.util.List;
import java.util.UUID;

/**
 * Asi como Client Service espera y actualiza los datos de manera dinamica,
 * TransactionHistoryService lo hace para el historial de Transacciones.
 */
public class TransactionHistoryService implements CRUD<TransactionHistory> {
	private final TransactionHistoryDAO transactionHistoryDAO;
	private Listed<TransactionHistory> transactionsHistories;

	public TransactionHistoryService(
			TransactionHistoryDAO transactionHistoryDAO
	) {
		this.transactionHistoryDAO = transactionHistoryDAO;
		reload();
	}

	public Thread listener(
			Runnable runnable
	) {
		Thread thread = new Thread( new FileListener(
				100,
				getFilePath(),
				() -> {
					this.reload();
					runnable.run();
				}
		) );

		thread.start();

		return thread;
	}

	public Thread listener() {
		Thread thread = new Thread( new FileListener(
				1000,
				getFilePath(),
				this::reload
		) );
		thread.start();

		return thread;
	}

	public void reload() {
		transactionsHistories = new Listed<>( transactionHistoryDAO.getAll() );
		Logger.log(Logger.LogLevel.SUCCESS, "Transactions History data loaded successfully!");
		System.out.println("Loading Transactions History Data done!");
	}

	@Override
	public String getFilePath() {
		return transactionHistoryDAO.getFilePath();
	}

	@Override
	public void create(TransactionHistory transactionHistory) {
		transactionHistoryDAO.create(transactionHistory);
		transactionsHistories.create(transactionHistory);
	}

	@Override
	public void add(TransactionHistory transactionHistory) throws ObjectAlreadyExistsException {
		transactionHistoryDAO.add(transactionHistory);
		transactionsHistories.add(transactionHistory);
	}

	@Override
	public TransactionHistory get(UUID uuid) throws ObjectNotFoundException {
		return transactionsHistories.get(uuid);
	}

	@Override
	public boolean update(TransactionHistory transactionHistory) throws ObjectNotFoundException, OperationFailedException {
		transactionsHistories.set(transactionHistory);
		return transactionHistoryDAO.update(transactionHistory);
	}

	@Override
	public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
		transactionsHistories.remove(uuid);
		return transactionHistoryDAO.delete(uuid);
	}

	@Override
	public List<TransactionHistory> getAll() {
		return transactionsHistories.getList();
	}

	public List<TransactionHistory> getAll(
			TransactionHistory.Status status,
			TransactionHistory.Type type,
			Long amount,
			double balanceMin,
			double balanceMax
	) {
		return transactionHistoryDAO.getAll(
				status,
				type,
				amount,
				balanceMin,
				balanceMax
		);
	}

}
