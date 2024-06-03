package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.View.Operation.TransferView;
import mateourrutia.View.Window;

import javax.swing.*;

public class TransferController extends DepositWithdrawUtils<TransferView> {

	public TransferController(
			Window owner,
			Account account,
			AccountDAO accountDAO,
			TransactionHistoryDAO transactionHistoryDAO
	) {
		super(owner, new TransferView(), account, accountDAO, transactionHistoryDAO);
	}

	protected void initController() {}

	@Override
	public void onAccept() {
		if (
				!isValidDouble(getInnerView().getBalance().getText()) ||
				!isValidLong(getInnerView().getCBU().getText())
		) {
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Por favor, solo numeros estan habilitados",
					"UNFORESEEN CONSEQUENCES",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		try {
			Account transferedAccount = getAccountDAO().get( Long.parseLong(getInnerView().getCBU().getText()) );

			TransactionHistory transactionResult = getAccount().transfer(
					Double.parseDouble( getInnerView().getBalance().getText() ),
					transferedAccount
			);

			getTransactionHistoryDAO().add(transactionResult);
			getAccountDAO().update(getAccount(), getAccount().getClient());
			getAccountDAO().update(transferedAccount, transferedAccount.getClient());

			ErrorHandling(transactionResult);
		}
		catch (Exception e) {
			ErrorController.show(getView(), e);
		}
	}

	@Override
	public void onCancel() {}
}
