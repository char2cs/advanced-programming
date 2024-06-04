package mateourrutia.Controller.Operation;

import mateourrutia.Controller.ErrorController;
import mateourrutia.DAO.AccountDAO;
import mateourrutia.DAO.TransactionHistoryDAO;
import mateourrutia.Domain.*;
import mateourrutia.Exceptions.ObjectNotFoundException;
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

	protected void initController() {
		if ( getAccount() instanceof Wallet )
			getInnerView().getCrypto().addItem("USD");

		for (Currency currency : Currency.values())
			getInnerView().getCrypto().addItem(currency);
	}

	private Wallet getToAccount(
			Currency currency
	) {
		Client client = getAccount().getClient();

		for ( Account account : client.getAccounts().getList() )
			if ( account instanceof Wallet )
				if ( ((Wallet) account).getCryptocurrency().getName() == currency )
					return (Wallet) account;

		return null;
	}

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

		final Account[] toAccounts = new Account[1];
		String balanceString = getInnerView().getBalance().getText();
		Double balance = Double.parseDouble(getInnerView().getBalance().getText());

		if ( getInnerView().getCrypto().getSelectedIndex() == 0 && getAccount() instanceof Wallet )
		{
			TransferController transferController = new TransferController(
					getParent(),
					getAccount(),
					getAccountService(),
					getTransactionHistoryService()
			) {
				@Override
				protected void initController() {
					super.initController();

					this.getInnerView().getBalance().setText( balanceString );
					this.getInnerView().getBalance().setEditable( false );
					this.getInnerView().getTitle().setText("Cuenta en Dolares para transferir");
				}

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
						toAccounts[0] = transferedAccount;
					}
					catch (ObjectNotFoundException e) {
						ErrorController.show(getView(), e);
					}
				}
			};

			this.closeDialog();
			transferController.showDialog();
		}
		else {
			Currency currency = Currency.values()[
				getAccount() instanceof Wallet ?
					getInnerView().getCrypto().getSelectedIndex() - 1 :
					getInnerView().getCrypto().getSelectedIndex()
			];
			toAccounts[0] = getToAccount(currency);
		}

		if ( toAccounts[0] == null )
		{
			JOptionPane.showMessageDialog(
					getInnerView(),
					"Cuenta seleccionada no existe / usted no posee una Wallet de aquella cryptomoneda",
					"Conversion fallida!",
					JOptionPane.WARNING_MESSAGE
			);
			return;
		}

		Account toAccount = toAccounts[0];

		try {
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
