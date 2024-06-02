package mateourrutia.Controller.Tables;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Domain.Client;
import mateourrutia.Controller.TableTypes.SimpleTable;
import mateourrutia.Services.ClientService;

import javax.swing.table.DefaultTableModel;

public class ClientSimple extends SimpleTable<Client> {
	private final ClientService clientService;

	public ClientSimple(
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
					return false;
				}
			};

			this.setModel(tableModel);
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			ErrorController.show( this.getView(), e );
		}
	}
}
