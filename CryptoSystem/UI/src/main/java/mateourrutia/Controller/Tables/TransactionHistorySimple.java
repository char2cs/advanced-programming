package mateourrutia.Controller.Tables;

import mateourrutia.Controller.TableTypes.SimpleTable;
import mateourrutia.Domain.TransactionHistory;

import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;

public class TransactionHistorySimple extends SimpleTable<TransactionHistory> {
	public TransactionHistorySimple(
			List<TransactionHistory> transactionHistories
	) {
		super();
		setModel( transactionHistories );
	}

	public void setModel(
			List<TransactionHistory> transactionHistories
	) {
		DefaultTableModel tableModel = new DefaultTableModel(
				convert( transactionHistories ),
				new Object[]{"Fecha", "Estado", "Tipo", "Cantidad", "De", "Hacia"}
		) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if ( columnIndex == 0 )
					return Date.class;

				if ( columnIndex == 1 )
					return TransactionHistory.Status.class;

				if ( columnIndex == 2 )
					return TransactionHistory.Type.class;

				if ( columnIndex == 3 )
					return double.class;

				if ( columnIndex == 4 )
					return Long.class;

				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		super.setModel(tableModel, true);
	}

	private Object[][] convert(List<TransactionHistory> transactionHistories) {
		if (transactionHistories == null || transactionHistories.isEmpty())
			return new Object[0][0];

		Object[][] transactionArray = new Object[transactionHistories.size()][6];

		for (int i = 0; i < transactionHistories.size(); i++)
		{
			TransactionHistory transactionHistory = transactionHistories.get(i);
			transactionArray[i][0] = transactionHistory.getDate();
			transactionArray[i][1] = transactionHistory.getStatus();
			transactionArray[i][2] = transactionHistory.getType();
			transactionArray[i][3] = transactionHistory.getAmount();
			transactionArray[i][4] = transactionHistory.getFromAccount().getCbu();

			if ( transactionHistory.getToAccount() != null )
				transactionArray[i][5] = transactionHistory.getToAccount().getCbu();
			else
				transactionArray[i][5] = "PERSONAL";
		}

		return transactionArray;
	}

}
