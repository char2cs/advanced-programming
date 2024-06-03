package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.View.Operation.WithdrawView;
import mateourrutia.View.Window;

import javax.swing.*;

public class WithdrawController extends DepositWithdrawUtils<WithdrawView> {

	public WithdrawController(
			Window owner,
			Account account,
			AccountDAO accountDAO,
			TransactionHistoryDAO transactionHistoryDAO
	) {
		super(owner, new WithdrawView(), account, accountDAO, transactionHistoryDAO);
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
			TransactionHistory transactionResult = getAccount().withdraw( Double.parseDouble( getInnerView().getBalance().getText() ) );

			getTransactionHistoryDAO().add(transactionResult);
			getAccountDAO().update(getAccount(), getAccount().getClient());

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"Su retiro de dinero es incorrecto, no cuenta con los fondos suficientes",
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.ERROR_MESSAGE
				);

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_OVERDRAFT_ISSUE) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"Ha superado el limite para transferir su dinero, pruebe con otro tipo de cuenta",
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.ERROR_MESSAGE
				);
		}
		catch (Exception e) {
			ErrorController.show(getView(), e);
		}
	}

	@Override
	public void onCancel() {

	}
}
