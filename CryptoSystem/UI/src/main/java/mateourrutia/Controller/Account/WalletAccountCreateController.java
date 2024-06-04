package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.StaticDialogController;
import mateourrutia.Domain.*;
import mateourrutia.Service.AccountService;
import mateourrutia.View.Account.WalletAccountCreateView;
import mateourrutia.View.Window;

import javax.swing.*;

public class WalletAccountCreateController extends StaticDialogController<WalletAccountCreateView> {
	private 		Client 			client;
	private final 	AccountService 	accountService;

	public WalletAccountCreateController(
			Window 			owner,
			Client 			client,
			AccountService	accountService
	) {
		super( owner, new WalletAccountCreateView() );
		this.client = client;
		this.accountService = accountService;
		initController();
	}

	private void initController() {
		for (Currency currency : Currency.values())
			getInnerView().getDropdown().addItem(currency);
	}

	@Override
	public void onAccept() {
		Currency currency = Currency.values()[ getInnerView().getDropdown().getSelectedIndex() ];

		if ( checkIfRepeated(currency) )
		{
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Usted ya tiene una Wallet de esta criptomoneda",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		Wallet wallet = new Wallet(
				client,
				0,
				new Cryptocurrency(currency)
		);

		try {
			accountService.add(wallet, client);
		}
		catch (Exception e) {
			ErrorController.show( getView(), e );
		}
	}

	@Override
	public void onCancel() {}

	private boolean checkIfRepeated(Currency currency) {
		for ( Account account : client.getAccounts().getList() )
			if ( account instanceof Wallet )
				if ( ((Wallet) account).getCryptocurrency().getName() == currency )
					return true;

		return false;
	}
}
