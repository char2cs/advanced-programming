package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.StaticDialogController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.View.Account.CheckingAccountCreateView;
import mateourrutia.View.Window;
import mateourrutia.utils.Listed;

import javax.swing.*;

public class CheckingAccountCreateController extends StaticDialogController<CheckingAccountCreateView>  {
	private final Client 		client;
	private final AccountDAO 	accountDAO;

	public CheckingAccountCreateController(
			Window 		window,
			Client 		client,
			AccountDAO 	accountDAO
	) {
		super(window, new CheckingAccountCreateView());
		this.client = client;
		this.accountDAO = accountDAO;
	}

	public CheckingAccount createCheckingAccount() {
		if ( !isValidDouble(getInnerView().getOverdraft()) )
		{
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Por favor, solo numeros estan habilitados",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return null;
		}

		return new CheckingAccount(
				client,
				0,
				Double.parseDouble(getInnerView().getOverdraft())
		);
	}

	private boolean isValidDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

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

	@Override
	public void onAccept() {
		if ( !checkCheckingAccount() )
		{
			JOptionPane.showMessageDialog(
					getView(),
					"Por favor, selecciona otro tipo de cuenta, ya tiene una checking account",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		CheckingAccount checkingAccount = createCheckingAccount();

		if ( checkingAccount == null )
			return;

		try {
			client.getAccounts().add(checkingAccount);
			accountDAO.add(checkingAccount, client);
		}
		catch (Exception e) {
			ErrorController.show( getView(), e );
		}
	}

	@Override
	public void onCancel() {}
}
