package mateourrutia.Controller.Account;

import mateourrutia.Controller.StaticDialogController;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.View.Account.CheckingAccountCreateView;
import mateourrutia.View.Window;

import javax.swing.*;

public class CheckingAccountCreateController {
	private final Client client;
	private final CheckingAccountCreateView view = new CheckingAccountCreateView();

	public CheckingAccountCreateController(
			Client client
	) {
		this.client = client;
	}

	public CheckingAccountCreateView getView() {
		return view;
	}

	public CheckingAccount createCheckingAccount() {
		if ( !isValidDouble(view.getOverdraft()) )
		{
			JOptionPane.showMessageDialog(
					view,
					"Por favor, solo numeros estan habilitados",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return null;
		}

		return new CheckingAccount(
				client,
				0,
				Double.parseDouble(view.getOverdraft())
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

	public static CheckingAccount showDialog(
			Window owner,
			Client client
	) {
		CheckingAccountCreateController controller = new CheckingAccountCreateController(client);

		if ( !StaticDialogController.showStaticDialog(
				owner,
				controller.getView()
		) )
			return null;

		return controller.createCheckingAccount();
	}
}
