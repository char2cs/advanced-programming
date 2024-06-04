package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Service.AccountService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.Operation.DepositView;
import mateourrutia.View.Window;

import javax.swing.*;

public class DepositController extends DepositWithdrawUtils<DepositView> {
	public DepositController(
			Window 						owner,
			Account 					account,

			AccountService 				accountService,
			TransactionHistoryService 	transactionHistoryService
	) {
		super(owner, new DepositView(), account, accountService, transactionHistoryService);
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
			getTransactionHistoryService().add(transactionResult);
			getAccountService().update(
					transactionResult.getFromAccount(),
					transactionResult.getFromAccount().getClient()
			);

			ErrorHandling(transactionResult);
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
