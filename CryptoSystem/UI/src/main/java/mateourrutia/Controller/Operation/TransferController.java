package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Service.AccountService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.Operation.TransferView;
import mateourrutia.View.Window;

import javax.swing.*;

public class TransferController extends DepositWithdrawUtils<TransferView> {

	public TransferController(
			Window owner,
			Account account,
			AccountService accountService,
			TransactionHistoryService transactionHistoryService
	) {
		super(owner, new TransferView(), account, accountService, transactionHistoryService);
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
			Account transferedAccount = getAccountService().get( Long.parseLong(getInnerView().getCBU().getText()) );

			TransactionHistory transactionResult = getAccount().transfer(
					Double.parseDouble( getInnerView().getBalance().getText() ),
					transferedAccount
			);

			getTransactionHistoryService().add(transactionResult);
			getAccountService().update(
					transactionResult.getFromAccount(),
					transactionResult.getFromAccount().getClient()
			);
			getAccountService().update(
					transactionResult.getToAccount(),
					transactionResult.getToAccount().getClient()
			);

			ErrorHandling(transactionResult);
		}
		catch (Exception e) {
			ErrorController.show(getView(), e);
		}
	}

	@Override
	public void onCancel() {}
}
