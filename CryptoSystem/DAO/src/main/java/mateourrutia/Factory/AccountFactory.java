package mateourrutia.Factory;

import mateourrutia.DAO.AccountDAO;
import mateourrutia.Imp.FileWriter.AccountImp;

public class AccountFactory {
	public static AccountDAO getAccountDAO(
			String type
	) {
		if ( type.equalsIgnoreCase("FileWriter") )
			return new AccountImp();
		else
			return new AccountImp();
	}
}
