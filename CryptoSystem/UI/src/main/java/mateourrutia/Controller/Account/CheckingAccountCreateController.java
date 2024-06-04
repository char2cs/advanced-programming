package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.StaticDialogController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Service.AccountService;
import mateourrutia.View.Account.CheckingAccountCreateView;
import mateourrutia.View.Window;
import mateourrutia.utils.Listed;

import javax.swing.*;

public class CheckingAccountCreateController extends StaticDialogController<CheckingAccountCreateView>  {
	private final Client 			client;
	private final AccountService accountService;

	public CheckingAccountCreateController(
			Window 			window,
			Client 			client,
			AccountService 	accountDAO
	) {
		super(window, new CheckingAccountCreateView());
		this.client = client;
		this.accountService = accountDAO;
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

		if ( !isValidDouble(getInnerView().getOverdraft()) )
		{
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Por favor, solo numeros estan habilitados",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		try {
			accountService.add(new CheckingAccount(
					client,
					0,
					Double.parseDouble(getInnerView().getOverdraft())
			), client);
		}
		catch (Exception e) {
			ErrorController.show( getView(), e );
		}
	}

	@Override
	public void onCancel() {}
}
