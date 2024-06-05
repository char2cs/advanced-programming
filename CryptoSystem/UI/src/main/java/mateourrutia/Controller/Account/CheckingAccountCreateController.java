package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.StaticDialogController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.Currency.Currency;
import mateourrutia.Service.AccountService;
import mateourrutia.View.Account.CheckingAccountCreateView;
import mateourrutia.View.Window;
import mateourrutia.utils.Listed;

import javax.swing.*;

public class CheckingAccountCreateController extends StaticDialogController<CheckingAccountCreateView>  {
	private final Client 			client;
	private final AccountService 	accountService;

	public CheckingAccountCreateController(
			Window 			window,
			Client 			client,
			AccountService 	accountDAO
	) {
		super(window, new CheckingAccountCreateView());
		this.client = client;
		this.accountService = accountDAO;

		for ( Currency currency : Currency.values() )
			getInnerView().getCurrency().addItem( currency );
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

	private boolean checkCheckingAccount(
			Currency currency
	) {
		Listed<Account> accounts = client.getAccounts();
		boolean result = true;

		for ( Account account : accounts.getList() )
			if ( account.getClass().getSimpleName().equals("CheckingAccount") && account.getCurrency().equals(currency) )
			{
				result = false;
				break;
			}

		return result;
	}

	@Override
	public void onAccept() {
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

		Currency currency = Currency.values()[getInnerView().getCurrency().getSelectedIndex()];

		if ( !checkCheckingAccount(currency) )
		{
			JOptionPane.showMessageDialog(
					getView(),
					"Por favor, selecciona otro tipo de cuenta, ya tiene una cuenta corriente en " + currency,
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		try {
			accountService.add(new CheckingAccount(
					client,
					0,
					Double.parseDouble(getInnerView().getOverdraft()),
					currency
			), client);
		}
		catch (Exception e) {
			ErrorController.show( getView(), e );
		}
	}

	@Override
	public void onCancel() {}
}
