package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.Domain.*;
import mateourrutia.Service.AccountService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.Operation.ConvertView;
import mateourrutia.View.Window;

import javax.swing.*;

public class ConvertController extends DepositWithdrawUtils<ConvertView> {

	public ConvertController(
			Window 						owner,
			Account 					account,
			AccountService 				accountService,
			TransactionHistoryService 	transactionHistoryDAO
	) {
		super(owner, new ConvertView(), account, accountService, transactionHistoryDAO);
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
			Double balance = Double.parseDouble(getInnerView().getBalance().getText());
			Account toAccount = getAccountService().get( Long.parseLong(getInnerView().getCBU().getText()) );

			TransactionHistory transactionResult = getAccount().convert(
					balance,
					toAccount
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
