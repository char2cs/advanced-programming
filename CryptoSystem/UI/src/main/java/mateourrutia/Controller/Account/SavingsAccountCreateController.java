package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.SavingsAccount;
import mateourrutia.View.ClientOverviewView;

import javax.swing.*;

public class SavingsAccountCreateController {

	public static void SavingsAccountSelected(
			Client 				client,
			AccountDAO 			accountDAO,
			ClientOverviewView 	clientOverviewView
	) {
		if (JOptionPane.showConfirmDialog(null, "Estas seguro de crear una nueva Cuenta Bancaria?", "Confirmar",
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
