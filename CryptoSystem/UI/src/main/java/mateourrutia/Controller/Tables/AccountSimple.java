package mateourrutia.Controller.Tables;

import mateourrutia.Controller.TableTypes.SimpleTable;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.CheckingAccount;
import mateourrutia.Domain.Client;
import mateourrutia.Domain.Wallet;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AccountSimple extends SimpleTable<Account> {
	private final Client client;

	public AccountSimple(
			Client client
	) {
		super();

		this.client = client;

		DefaultTableModel tableModel = new DefaultTableModel(
				convert( client.getAccounts().getList() ),
				new Object[]{"CBU", "Balance", "Limite para retirar", "Tipo"}
		) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if ( columnIndex == 0 )
					return Long.class;

				if ( columnIndex == 1 )
					return Double.class;

				if ( columnIndex == 2 )
					return Double.class;

				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		this.setModel(tableModel);
	}

	private Object[][] convert(List<Account> accounts) {
		if (accounts == null || accounts.isEmpty())
			return new Object[0][0];

		Object[][] accountArray = new Object[accounts.size()][4];

		for (int i = 0; i < accounts.size(); i++)
		{
			Account account = accounts.get(i);
			accountArray[i][0] = account.getCbu();

			if ( account.getClass().getSimpleName().equals("CheckingAccount") )
			{
				assert account instanceof CheckingAccount;
				accountArray[i][1] = account.getBalance();
				accountArray[i][2] = ((CheckingAccount) account).getOverdraftLimit();
			}
			else {
				accountArray[i][1] = account.getBalance();
				accountArray[i][2] = null;
			}

			accountArray[i][3] = account.getClass().getSimpleName() + " en " + (account).getCurrency();
		}

		return accountArray;
	}
}
