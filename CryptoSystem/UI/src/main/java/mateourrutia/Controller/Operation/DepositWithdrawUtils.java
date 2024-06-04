package mateourrutia.Controller.Operation;

import mateourrutia.Controller.StaticDialogController;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Service.AccountService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.Window;

import javax.swing.*;

public abstract class DepositWithdrawUtils<T extends JPanel> extends StaticDialogController<T> {
	private final Account 					account;
	private final AccountService 			accountService;
	private final TransactionHistoryService transactionHistoryService;

	public DepositWithdrawUtils(
			Window 						owner,
			T 							view,
			Account 					account,
			AccountService 				accountDAO,
			TransactionHistoryService 	transactionHistoryService
	) {
		super( owner, view );
		this.account = account;
		this.accountService = accountDAO;
		this.transactionHistoryService = transactionHistoryService;

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

	protected AccountService getAccountService() {
		return accountService;
	}

	protected TransactionHistoryService getTransactionHistoryService() {
		return transactionHistoryService;
	}

	protected void ErrorHandling(
			TransactionHistory transactionHistory
	) {
		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.UNKNOWN_ERROR) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Se ha producio un error desconocido",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Su retiro de dinero es incorrecto, no cuenta con los fondos suficientes",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_OVERDRAFT_ISSUE) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Ha superado el limite para transferir su dinero, pruebe con otro tipo de cuenta",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_ACCOUNT_IS_NOT_WALLET) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"El CBU destino no es una Wallet, conversion no se puede realizar.",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_ACCOUNT_IS_WALLET) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"El CBU destino es una Wallet, conversion no se puede realizar.",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_WALLETS_ARE_NOT_FROM_SAME_CLIENT) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Las cuentas no son del mismo cliente.",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_ACCOUNTS_ARE_THE_SAME_ACCOUNT) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"El CBU destino pertenece a esta misma cuenta",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);

		if ( transactionHistory.getStatus().equals(TransactionHistory.Status.ERROR_WALLETS_ARE_DIFFERENT_TYPE) )
			JOptionPane.showMessageDialog(
					getInnerView(),
					"La Wallet destino es de otra crypto, opta por una conversion y luego una transferencia.",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.ERROR_MESSAGE
			);
	}
}
