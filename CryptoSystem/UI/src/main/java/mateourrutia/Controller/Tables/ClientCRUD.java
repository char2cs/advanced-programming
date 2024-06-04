package mateourrutia.Controller.Tables;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.TableTypes.GeneralTable;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.SavingsAccount;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.Service.ClientService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.Tables.ClientCRUDView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientCRUD extends GeneralTable<Client> {
	private final ClientService clientService;
	private final TransactionHistoryService transactionHistoryService;

	public ClientCRUD(
			ClientService clientService,
			TransactionHistoryService transactionHistoryService
	) {
		super();
		this.clientService = clientService;
		this.transactionHistoryService = transactionHistoryService;

		try {
			setModel( clientService.getAll() );
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		initController();
	}

	private void setModel(
			List<Client> clients
	) throws IllegalAccessException {
		DefaultTableModel tableModel = new DefaultTableModel(
				convertToTableData( clients ),
				new Object[]{"CUIT", "Nombre", "Apellido", "Telefono", "Email", "Direccion"}
		) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if ( columnIndex == 0 )
					return Long.class;

				if ( columnIndex == 3 )
					return Long.class;

				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};

		this.setModel(tableModel);
	}

	private void initController() {
		ClientCRUDView clientCRUDView = new ClientCRUDView();
		getView().add(clientCRUDView, BorderLayout.SOUTH);

		clientCRUDView.getGoBack().addActionListener(e -> {
			getWindow().dispose();
		});

		clientCRUDView.getTestingCases().addActionListener(e -> {
			Client test1 = new Client(
					20455183406L,
					"Mateo",
					"Urrutia",
					1128447029L,
					"mateo@rounds.com.ar",
					"Ituzaingo 670"
			);

			Client test2 = new Client(
					20954133436L,
					"Martina",
					"Conti",
					1128448023L,
					"martina@rounds.com.ar",
					"Chacabuco 1620"
			);

			try {
				// Agregamos los clientes
				clientService.add(test1);
				clientService.add(test2);

				// Agregamos las cuentas
				clientService.getAccountService().add(
						new SavingsAccount(
								test1,
								20000,
								test1.getCuit()
						),
						test1
				);
				clientService.getAccountService().add(
						new CheckingAccount(
								test2,
								100,
								300
						),
						test2
				);

				// Realizamos la transaccion
				TransactionHistory transactionHistory = test1.getAccounts().get(0).transfer(
						150,
						test2.getAccounts().get(0)
				);

				clientService.getAccountService().update(transactionHistory.getFromAccount(), transactionHistory.getFromAccount().getClient());
				clientService.getAccountService().update(transactionHistory.getToAccount(), transactionHistory.getToAccount().getClient());

				transactionHistoryService.add(transactionHistory);

				getWindow().dispose(); // Volvemos al inicio
			}
			catch (Exception ex) {
				ErrorController.show( this.getView(), ex );
			}
		});
	}

	@Override
	protected Client onAdd() throws ClassCastException {
		Client client = new Client(
				null,
				"",
				"",
				null,
				"",
				""
		);

		try {
			clientService.add(client);
		}
		catch (ObjectAlreadyExistsException e) {
			e.printStackTrace();
			ErrorController.show( this.getView(), e );
		}

		return client;
	}

	@Override
	protected void onUpdate(TableModelEvent e) throws ClassCastException {
		DefaultTableModel Model = this.getModel();
		int row 				= e.getFirstRow();
		int column 				= e.getColumn();

		Object something 		= Model.getValueAt(row, column);
		List<Client> clients 	= clientService.getAll();
		Client client 			= clients.get(row);

		switch (column) {
			case 0:
				client.setCuit( (Long) something );
				break;

			case 1:
				client.setName( (String) something );
				break;

			case 2:
				client.setLastname( (String) something );
				break;

			case 3:
				client.setPhone( (Long) something );
				break;

			case 4:
				client.setEmail( (String) something );
				break;

			case 5:
				client.setAddress( (String) something );
				break;
		}

		try {
			clientService.update(client);
		}
		catch (ObjectNotFoundException ex) {
			ex.printStackTrace();
			ErrorController.show( this.getView(), ex );
		}
		catch (OperationFailedException ex) {
			ex.printStackTrace();
			ErrorController.show( this.getView(), ex );
		}
	}

	@Override
	protected void onDelete(Integer[] rows, JTable table) throws ClassCastException {
		List<Client> clients = clientService.getAll();

		for (int row : rows)
			try {
				clientService.delete( clients.get( row ).getUuid() );
				this.removeRow( row );
			}
			catch (ObjectNotFoundException e) {
				e.printStackTrace();
				ErrorController.show( this.getView(), e );
			}
			catch (OperationFailedException e) {
				e.printStackTrace();
				ErrorController.show( this.getView(), e );
			}
	}

}
