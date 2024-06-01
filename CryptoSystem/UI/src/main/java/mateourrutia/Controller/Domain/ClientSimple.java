package mateourrutia.Controller.Domain;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Controller.TableTypes.SimpleTable;
import mateourrutia.Factory.ClientFactory;

import javax.swing.table.DefaultTableModel;

public class ClientSimple extends SimpleTable<Client> {
	private final ClientDAO clientDAO = ClientFactory.getClientDAO("TypeWriter");

	public ClientSimple() {
		super();

		try {
			DefaultTableModel tableModel = new DefaultTableModel(
					convertToTableData( clientDAO.getAll() ),
					new Object[]{"CUIT", "Nombre", "Apellido", "Telefono", "Email", "Direccion"}
			) {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					if ( columnIndex == 0 )
						return Integer.class;

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
			throw new RuntimeException(e);
		}
	}
}
