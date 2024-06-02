package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.StaticDialogController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.SavingsAccount;
import mateourrutia.View.ClientOverviewView;
import mateourrutia.View.Window;
import mateourrutia.utils.Listed;

import javax.swing.*;

/**
 * Actua de Helper para la creacion de cuentas.
 */
public class AccountCreatorListener {
	private Client client;
	private final AccountDAO accountDAO;
	private ClientOverviewView clientOverviewView;
	private final Window window;

	public AccountCreatorListener(
			Client client,
			AccountDAO accountDAO,
			ClientOverviewView clientOverviewView,
			Window window
	) {
		this.client = client;
		this.accountDAO = accountDAO;
		this.clientOverviewView = clientOverviewView;
		this.window = window;
	}

	// Checking Account
	private boolean checkCheckingAccount() {
		Listed<Account> accounts = client.getAccounts();
		boolean result = true;

		for ( Account account : accounts.getList() )
			if (account.getClass().getSimpleName().equals("CheckingAccount"))
			{
				result = false;
				break;
			}

		return result;
	}

	public void CheckingAccountSelected() {
		if ( !checkCheckingAccount() )
		{
			JOptionPane.showMessageDialog(
					clientOverviewView,
					"Por favor, selecciona otro tipo de cuenta, ya tiene una checking account",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		CheckingAccountCreateController checkingAccountController = new CheckingAccountCreateController(client);

		StaticDialogController staticDialogController = new StaticDialogController(window, checkingAccountController.getView()) {
			@Override
			public void onAccept() {
				CheckingAccount checkingAccount = checkingAccountController.createCheckingAccount();

				if ( checkingAccount == null )
					return;

				try {
					client.getAccounts().add(checkingAccount);
					accountDAO.add(checkingAccount, client);
				}
				catch (Exception e) {
					ErrorController.show( clientOverviewView, e );
				}
			}

			@Override
			public void onCancel() {}
		};

		staticDialogController.showDialog();
	}

	public void SavingsAccountSelected() {
		if (JOptionPane.showConfirmDialog(null, "Estas seguro de crear una Cuenta Bancaria?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
			return;

		try {
			SavingsAccount savingsAccount = new SavingsAccount(
					client,
					0,
					client.getCuit()
			);

			client.getAccounts().add(savingsAccount);
			accountDAO.add(savingsAccount, client);
		}
		catch (Exception e) {
			ErrorController.show( clientOverviewView, e );
		}
	}
}
