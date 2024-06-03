package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.View.Operation.DepositView;
import mateourrutia.View.Window;

import javax.swing.*;

public class DepositController extends DepositWithdrawUtils<DepositView> {
	public DepositController(
			Window owner,
			Account account,
			AccountDAO accountDAO,
			TransactionHistoryDAO transactionHistoryDAO
	) {
		super(owner, new DepositView(), account, accountDAO, transactionHistoryDAO);
	}

	protected void initController() {}

	@Override
	public void onAccept() {
		if ( !isValidDouble(getInnerView().getBalance().getText()) )
		{
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Por favor, solo numeros estan habilitados",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		try {
			TransactionHistory transactionResult = getAccount().deposit( Double.parseDouble( getInnerView().getBalance().getText() ) );
			getTransactionHistoryDAO().add(transactionResult);
			getAccountDAO().update(getAccount(), getAccount().getClient());
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorController.show(getView(), e);
		}
	}

	@Override
	public void onCancel() {

	}
}
