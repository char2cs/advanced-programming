package mateourrutia.Controller.Operation;

import mateourrutia.Controller.StaticDialogController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Account;
import mateourrutia.View.Window;

import javax.swing.*;

public abstract class DepositWithdrawUtils<T extends JPanel> extends StaticDialogController<T> {
	private final Account account;
	private final AccountDAO accountDAO;
	private final TransactionHistoryDAO transactionHistoryDAO;

	public DepositWithdrawUtils(
			Window 					owner,
			T 						view,
			Account 				account,
			AccountDAO 				accountDAO,
			TransactionHistoryDAO 	transactionHistoryDAO
	) {
		super( owner, view );
		this.account = account;
		this.accountDAO = accountDAO;
		this.transactionHistoryDAO = transactionHistoryDAO;

		initController();
	}

	protected boolean isValidDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean isValidLong(String value) {
		try {
			Long.parseLong(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	protected abstract void initController();

	protected Account getAccount() {
		return account;
	}

	protected AccountDAO getAccountDAO() {
		return accountDAO;
	}

	protected TransactionHistoryDAO getTransactionHistoryDAO() {
		return transactionHistoryDAO;
	}
}
