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

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_ACCOUNTS_ARE_THE_SAME_ACCOUNT) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"El CBU destino pertenece a esta misma cuenta",
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.ERROR_MESSAGE
				);

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"Su retiro de dinero es incorrecto, no cuenta con los fondos suficientes",
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.ERROR_MESSAGE
				);

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_ACCOUNT_IS_WALLET) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"El CBU destino es una Wallet, conversion no se puede realizar.",
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.ERROR_MESSAGE
				);

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_ACCOUNT_IS_NOT_WALLET) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"El CBU destino no es una Wallet, conversion no se puede realizar.",
						"UNFORESEEN CONSEQUENCES",
						JOptionPane.ERROR_MESSAGE
				);

			if ( transactionResult.getStatus().equals(TransactionHistory.Status.ERROR_WALLETS_ARE_DIFFERENT_TYPE) )
				JOptionPane.showMessageDialog(
						getInnerView(),
						"La Wallet destino es de otra crypto, opta por una conversion y luego una transferencia.",
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
	public void onCancel() {}
}
