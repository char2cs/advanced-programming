package mateourrutia.Controller.Tables;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.TableTypes.GeneralTable;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.Services.ClientService;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClientCRUD extends GeneralTable<Client> {
	private final ClientService clientService;

	public ClientCRUD(
			ClientService clientService
	) {
		super();
		this.clientService = clientService;

		try {
			DefaultTableModel tableModel = new DefaultTableModel(
					convertToTableData( clientService.getClients() ),
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
		catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
			clientService.getClientDAO().add(client);
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
		List<Client> clients 	= clientService.getClients();
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
			clientService.getClientDAO().update(client);
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
		List<Client> clients = clientService.getClients();

		for (int row : rows)
			try {
				clientService.getClientDAO().delete( clients.get( row ).getUuid() );
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
