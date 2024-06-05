package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.StaticDialogController;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.Currency.Currency;
import mateourrutia.Domain.SavingsAccount;
import mateourrutia.Service.AccountService;
import mateourrutia.View.Account.SavingsAccountCreateView;
import mateourrutia.View.Window;
import mateourrutia.utils.Listed;

import javax.swing.*;


public class SavingsAccountCreateController extends StaticDialogController<SavingsAccountCreateView> {
	private final Client 			client;
	private final AccountService 	accountService;

	public SavingsAccountCreateController(
		Window 			window,
		Client 			client,
		AccountService 	accountDAO
	) {
		super(window, new SavingsAccountCreateView());
		this.client = client;
		this.accountService = accountDAO;

		for ( Currency currency : Currency.values() )
			getInnerView().getCurrency().addItem( currency );
	}

	private boolean checkSavingsAccount(
			Currency currency
	) {
		Listed<Account> accounts = client.getAccounts();
		boolean result = true;

		for ( Account account : accounts.getList() )
			if ( account.getClass().getSimpleName().equals("SavingsAccount") && account.getCurrency().equals(currency) )
			{
				result = false;
				break;
			}

		return result;
	}

	@Override
	public void onAccept() {
		Currency currency = Currency.values()[getInnerView().getCurrency().getSelectedIndex()];

		if ( !checkSavingsAccount(currency) )
		{
			JOptionPane.showMessageDialog(
					getView(),
					"Por favor, selecciona otro tipo de cuenta, ya tiene una cuenta de ahorro en " + currency,
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		try {
			accountService.add(new SavingsAccount(
					client,
					0,
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
