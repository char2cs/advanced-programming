package mateourrutia.Controller;

import mateourrutia.Controller.Account.AccountCreatorListener;
import mateourrutia.Controller.Tables.AccountSimple;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Factory.AccountFactory;
import mateourrutia.Factory.PersistenceType;
import mateourrutia.Services.ClientService;
import mateourrutia.View.ClientOverviewView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientOverviewController extends WindowController<ClientOverviewView> {
	private final ClientService clientService;
	private final Client client;
	private final AccountDAO accountDAO = AccountFactory.getAccountDAO(PersistenceType.FILEWRITER);
	private AccountSimple accountSimple;

	public ClientOverviewController(Client client, ClientService clientService) {
		super(new ClientOverviewView());

		this.client = client;
		this.clientService = clientService;
		initializeView();
	}

	private void initializeView() {
		setTitle("Overview: " + client.getLastname() + ", " + client.getName());
		getView().getClientName().setText(client.getLastname() + ", " + client.getName());

		getView().getBack().addActionListener(e -> getWindow().dispose());

		initializeCreateTypeDropdown();

		getView().getCreate().addActionListener(new CreateListener());
		getView().getDelete().addActionListener(new DeleteListener());

		setTable(client);
	}

	private void initializeCreateTypeDropdown() {
		JComboBox<String> createType = getView().getCreateType();
		createType.addItem("Tipo de cuenta:");
		createType.addItem("Checking Account");
		createType.addItem("Savings Account");
		createType.addItem("Wallet");
		createType.setSelectedIndex(0);
	}

	/**
	 * Como la actualizacion de los valores de Client es instantanea,
	 * no le da tiempo a ClientService a refrescar los objetos de cliente.
	 *
	 * Entonces, generalmente al usar este metodo forzamos los valores
	 * recuperandolos del archivo, usando el DAO de Account.
	 */
	private void setTable(Client client) {
		accountSimple = new AccountSimple(client);
		JPanel tablePanel = getView().getTable();
		tablePanel.removeAll();
		tablePanel.add(accountSimple.getView(), BorderLayout.CENTER);
		tablePanel.revalidate();
		tablePanel.repaint();
	}

	private class CreateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = getView().getCreateType().getSelectedIndex();
			if (selectedIndex == 0)
			{
				showMessageDialog("Por favor, selecciona un tipo de cuenta", JOptionPane.WARNING_MESSAGE);
				return;
			}

			AccountCreatorListener accountCreatorListener = new AccountCreatorListener(client, accountDAO, getView(), getWindow());

			switch (selectedIndex) {
				case 1:
					accountCreatorListener.CheckingAccountSelected();
					break;
				case 2:
					accountCreatorListener.SavingsAccountSelected();
					break;
			}

			try {
				setTable( clientService.getClientDAO().get(client.getUuid()) );
				clientService.loadClients();
			}
			catch (ObjectNotFoundException ex) {
				handleError(ex);
			}
		}
	}

	private class DeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedRow = accountSimple.getSelectedRowIndex();
			if (selectedRow == -1)
				return;

			Account account = client.getAccounts().get(selectedRow);
			if (account.getBalance() != 0)
			{
				showMessageDialog("Por favor, selecciona una Cuenta vacia.", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (confirmDeletion(account))
				try {
					accountDAO.delete(account.getUuid(), client);
					setTable(clientService.getClientDAO().get(client.getUuid()));
				}
				catch (Exception ex) {
					handleError(ex);
				}

		}

		private boolean confirmDeletion(Account account) {
			return JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar la cuenta " + account.getClass().getSimpleName() + "?", "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION;
		}
	}

}
