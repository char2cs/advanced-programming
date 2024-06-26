package mateourrutia.Controller;

import mateourrutia.Controller.Account.SavingsAccountCreateController;
import mateourrutia.Controller.Account.CheckingAccountCreateController;
import mateourrutia.Controller.Account.WalletAccountCreateController;
import mateourrutia.Controller.Tables.AccountSimple;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Factory.AccountFactory;
import mateourrutia.Factory.PersistenceType;
import mateourrutia.Service.ClientService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.AccountOverviewView;
import mateourrutia.View.ClientOverviewView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ABM de Cuentas del usuario seleccionado, como tambien
 * la interfaz que nos permite controlar y manejar nuestras cuentas.
 */
public class ClientOverviewController extends WindowController<ClientOverviewView> {
	private final 	ClientService 				clientService;
	private final 	TransactionHistoryService 	transactionHistoryService;

	private 		Client 						client;
	private 		AccountSimple 				accountSimple;

	public ClientOverviewController(
			ClientOverviewView 			clientOverviewView,
			Client 						client,
			ClientService 				clientService,
			TransactionHistoryService 	transactionHistoryService
	) {
		super(clientOverviewView);

		this.client = client;
		this.clientService = clientService;
		this.transactionHistoryService = transactionHistoryService;

		initializeView();
	}

	private void initializeView() {
		setTitle("Overview: " + client.getLastname() + ", " + client.getName());
		getView().getClientName().setText(client.getLastname() + ", " + client.getName());

		getView().getSelectedAccount().addActionListener(new AccountSelectListener());
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
		accountSimple.allowSelection();

		JPanel tablePanel = getView().getTable();
		tablePanel.removeAll();
		tablePanel.add(accountSimple.getView(), BorderLayout.CENTER);
		tablePanel.revalidate();
		tablePanel.repaint();
	}

	/**
	 * Reload user
	 */
	private void reloadUser() {
        try {
			clientService.reload();
            client = clientService.get(client.getUuid());
			setTable(client);
        }
		catch (ObjectNotFoundException e) {
            handleError(e);
        }
    }

	/**
	 * Abre la interfaz de la cuenta
	 */
	private class AccountSelectListener extends OpenAnotherWindowListener<AccountOverviewController> {
		@Override
		protected AccountOverviewController Object() {
			List<Account> accounts = client.getAccounts().getList();
			int row = accountSimple.getSelectedRowIndex();

			if ( row == -1 )
				return null;

			return new AccountOverviewController(
					new AccountOverviewView(),
					accounts.get(row),
					clientService.getAccountService(),
					transactionHistoryService
			);
		}

		@Override
		protected void onClose() {
			reloadUser();
		}
	}

	/**
	 * Se encarga de manejar la creacion de cuentas
	 */
	private class CreateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = getView().getCreateType().getSelectedIndex();
			if (selectedIndex == 0)
			{
				showMessageDialog("Por favor, selecciona un tipo de cuenta", JOptionPane.WARNING_MESSAGE);
				return;
			}

			switch (selectedIndex) {
				case 1:
					CheckingAccountCreateController checkingAccountCreateController = new CheckingAccountCreateController(
							getWindow(),
							client,
							clientService.getAccountService()
					);
					checkingAccountCreateController.showDialog();
					break;
				case 2:
					SavingsAccountCreateController savingsAccountCreateController = new SavingsAccountCreateController(
							getWindow(),
							client,
							clientService.getAccountService()
					);
					savingsAccountCreateController.showDialog();
					break;

				case 3:
					WalletAccountCreateController wallet = new WalletAccountCreateController( getWindow(), client, clientService.getAccountService() );
					wallet.showDialog();
					break;
			}

			try {
				setTable( clientService.get(client.getUuid()) );
			}
			catch (ObjectNotFoundException ex) {
				handleError(ex);
			}
		}
	}

	/**
	 * Maneja el borrado de las cuentas
	 */
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
					clientService.getAccountService().delete(account.getUuid(), client);
					setTable(clientService.get(client.getUuid()));
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
