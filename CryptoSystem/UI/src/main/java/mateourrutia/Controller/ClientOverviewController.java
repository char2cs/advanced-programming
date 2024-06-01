package mateourrutia.Controller;

import mateourrutia.Controller.Tables.AccountSimple;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Factory.AccountFactory;
import mateourrutia.View.ClientOverviewView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientOverviewController extends WindowController<ClientOverviewView> {
	private final Client client;
	private final AccountDAO accountDAO = AccountFactory.getAccountDAO("FileWriter");
	private final AccountSimple accountSimple;

	public ClientOverviewController(Client client) {
		super( new ClientOverviewView() );

		this.client = client;
		this.accountSimple = new AccountSimple(client);

		setTitle( "Overview: " + client.getLastname() + ", " + client.getName() );
		getView().getClientName().setText( client.getLastname() + ", " + client.getName() );

		getView().getBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getWindow().dispose();
			}
		});

		getView().getTable().add( accountSimple.getView(), BorderLayout.CENTER );

		getView().getCreateType().addItem("Tipo de cuenta:");
		getView().getCreateType().addItem("CheckingAccount");
		getView().getCreateType().addItem("Wallet");
		getView().getCreateType().addItem("SavingsAccount");
		getView().getCreateType().setSelectedIndex(0);

		getView().getDelete().addActionListener(new DeleteListener());
	}

	private class DeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Integer row = accountSimple.getSelectedRowIndex();

			if ( row == -1 )
				return;

			Account account = client.getAccounts().get(row);

			if ( account.getBalance() != 0 )
			{
				JOptionPane.showMessageDialog(
						getView(),
						"Por favor, selecciona una Cuenta vacia.",
						"*Almost* UNFORESEEN CONSEQUENCES",
						JOptionPane.WARNING_MESSAGE
				);
				return;
			}

			if (
					JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar la cuenta " + account.getClass().getSimpleName() + "?", "Confirmar",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION
			)
				return;

			try {
				accountDAO.delete(account.getUuid(), client);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				ErrorController.show( getView(), ex );
			}
		}
	}

}
