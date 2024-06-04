package mateourrutia.Controller.Account;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.SavingsAccount;
import mateourrutia.Service.AccountService;
import mateourrutia.View.ClientOverviewView;

import javax.swing.*;

public class SavingsAccountCreateController {

	public static void SavingsAccountSelected(
			Client 				client,
			AccountService 		accountService,
			ClientOverviewView 	clientOverviewView
	) {
		if (JOptionPane.showConfirmDialog(null, "Estas seguro de crear una nueva Cuenta Bancaria?", "Confirmar",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
			return;

		try {
			accountService.add(new SavingsAccount(
					client,
					0,
					client.getCuit()
			), client);
		}
		catch (Exception e) {
			ErrorController.show( clientOverviewView, e );
		}
	}

}
