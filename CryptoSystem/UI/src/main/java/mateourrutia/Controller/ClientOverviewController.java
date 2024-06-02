package mateourrutia.Controller;

import mateourrutia.Controller.Account.CheckingAccountCreateController;
import mateourrutia.Controller.Tables.AccountSimple;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Factory.AccountFactory;
import mateourrutia.Factory.PersistenceType;
import mateourrutia.View.ClientOverviewView;
import mateourrutia.utils.Listed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientOverviewController extends WindowController<ClientOverviewView> {
	private final Client client;
	private final AccountDAO accountDAO = AccountFactory.getAccountDAO(PersistenceType.FILEWRITER);
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
		getView().getCreateType().addItem("Checking Account");
		getView().getCreateType().addItem("Wallet");
		getView().getCreateType().addItem("Savings Account");
		getView().getCreateType().setSelectedIndex(0);

		getView().getCreate().addActionListener(new CreateListener());
		getView().getDelete().addActionListener(new DeleteListener());
	}

	private class CreateListener implements ActionListener {
		private void noAccountSelected() {
			JOptionPane.showMessageDialog(
					getView(),
					"Por favor, selecciona un tipo de cuenta",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
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

		private void CheckingAccountSelected() {
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

			try {
				CheckingAccount checkingAccount = CheckingAccountCreateController.showDialog(
						getWindow(),
						client
				);

				if ( checkingAccount == null )
					return;

				accountDAO.add(checkingAccount, client);
			}
			catch (Exception e) {
				ErrorController.show( getView(), e );
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Integer row = getView().getCreateType().getSelectedIndex();

			if ( row.equals(0) )
			{
				noAccountSelected();
				return;
			}
			else if ( row.equals(1) )
				CheckingAccountSelected();
			else if ( row.equals(2) ) return;


		}
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
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.WARNING_MESSAGE
				);
				return;
			}

			if (JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar la cuenta " + account.getClass().getSimpleName() + "?", "Confirmar",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
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
