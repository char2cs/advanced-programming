package mateourrutia.Controller.Tables;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Controller.TableTypes.SimpleTable;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Factory.PersistenceType;
import mateourrutia.Factory.TransactionHistoryFactory;

import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class TransactionHistorySimple extends SimpleTable<TransactionHistory> {
	private final TransactionHistoryDAO transactionHistoryDAO = TransactionHistoryFactory.getTransactionHistoryDAO(PersistenceType.STRINGWRITER);

	public TransactionHistorySimple() {
		super();

		try {
			DefaultTableModel tableModel = new DefaultTableModel(
					convertToTableData( transactionHistoryDAO.getAll() ),
					new Object[]{"Fecha", "Estado", "Tipo", "Cantidad", "De", "Hacia"}
			) {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					if ( columnIndex == 0 )
						return Date.class;

					if ( columnIndex == 1 )
						return TransactionHistory.Status.class;

					if ( columnIndex == 2 )
						return TransactionHistory.Status.class;

					if ( columnIndex == 3 )
						return double.class;

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
