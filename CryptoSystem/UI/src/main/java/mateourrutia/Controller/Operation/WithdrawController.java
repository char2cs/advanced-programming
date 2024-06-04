package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Service.AccountService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.Operation.WithdrawView;
import mateourrutia.View.Window;

import javax.swing.*;

public class WithdrawController extends DepositWithdrawUtils<WithdrawView> {

	public WithdrawController(
			Window owner,
			Account account,
			AccountService accountService,
			TransactionHistoryService transactionHistoryService
	) {
		super(owner, new WithdrawView(), account, accountService, transactionHistoryService);
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

			getTransactionHistoryService().add(transactionResult);
			getAccountService().update(
					transactionResult.getFromAccount(),
					transactionResult.getFromAccount().getClient()
			);

			ErrorHandling( transactionResult );
		}
		catch (Exception e) {
			ErrorController.show(getView(), e);
		}
	}

	@Override
	public void onCancel() {

	}
}
