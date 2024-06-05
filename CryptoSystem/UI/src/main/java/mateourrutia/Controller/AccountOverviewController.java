package mateourrutia.Controller;

import mateourrutia.Controller.Operation.ConvertController;
import mateourrutia.Controller.Operation.DepositController;
import mateourrutia.Controller.Operation.TransferController;
import mateourrutia.Controller.Operation.WithdrawController;
import mateourrutia.Domain.Account;
import mateourrutia.Domain.Wallet;
import mateourrutia.Service.AccountService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.AccountOverviewView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Nos permite visualizar una cuenta seleccionada.
 * Se encarga de ser el front de las transferencias,
 * depositos, converciones y retiros.
 */
public class AccountOverviewController extends WindowController<AccountOverviewView> {
	private final 	TransactionHistoryService 	transactionHistoryService;
	private 		Account 					account;
	private final 	AccountService 				accountService;

	public AccountOverviewController(
			AccountOverviewView 		view,
			Account 					account,
			AccountService 				accountService,
			TransactionHistoryService 	transactionHistoryService
	) {
		super( view );
		this.account 					= account;
		this.accountService			 	= accountService;
		this.transactionHistoryService 	= transactionHistoryService;

		initController();
	}

	private void initController() {
		String title = account.getClient().getLastname()
				.concat(", ")
				.concat(account.getClient().getName())
				.concat(". ")
				.concat(account.getClass().getSimpleName());

		title = title.concat(" en ")
				.concat( account.getCurrency().toString() );

		setTitle( title );
		getView().getAccount().setText( title );
		getView().getBack().addActionListener(e -> getWindow().dispose());
		getView().getDeposit().addActionListener(new DepositListener());
		getView().getWithdraw().addActionListener(new WithdrawListener());
		getView().getTransaction().addActionListener(new TransactionListener());
		getView().getConvert().addActionListener(new ConvertListener());
		updateBalance();
	}

	private class DepositListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DepositController depositController = new DepositController(
					getWindow(),
					account,
					accountService,
					transactionHistoryService
			);

			depositController.showDialog();

			updateBalance();
		}
	}

	private class WithdrawListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			WithdrawController withdrawController = new WithdrawController(
					getWindow(),
					account,
					accountService,
					transactionHistoryService
			);

			withdrawController.showDialog();

			updateBalance();
		}
	}

	private class TransactionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TransferController transferController = new TransferController(
					getWindow(),
					account,
					accountService,
					transactionHistoryService
			);

			transferController.showDialog();

			updateBalance();
		}
	}

	private class ConvertListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ConvertController convertController = new ConvertController(
					getWindow(),
					account,
					accountService,
					transactionHistoryService
			);

			convertController.showDialog();

			updateBalance();
		}
	}

	private void updateBalance() {
		getView().getBalance().setText(String.format("$%.2f", account.getBalance()));
	}

}