package mateourrutia.Controller;

import mateourrutia.Domain.Account;
import mateourrutia.Domain.Wallet;
import mateourrutia.View.AccountOverviewView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountOverviewController extends WindowController<AccountOverviewView> {
	private Account account;

	public AccountOverviewController(Account account) {
		super( new AccountOverviewView() );
		this.account = account;

		initController();
	}

	private void initController() {
		String title = account.getClient().getLastname()
				.concat(", ")
				.concat(account.getClient().getName())
				.concat(". ")
				.concat(account.getClass().getSimpleName());

		if ( account instanceof Wallet )
			title = title.concat(" en ")
					.concat( ((Wallet) account).getCryptocurrency().getName().toString() );

		setTitle( title );
		getView().getAccount().setText( title );
		getView().getBack().addActionListener(e -> getWindow().dispose());
		getView().getDeposit().addActionListener(new DepositListener());
		getView().getWithdraw().addActionListener(new WithdrawListener());
		getView().getTransaction().addActionListener(new TransactionListener());
		updateBalance();
	}

	private class DepositListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	private class WithdrawListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	private class TransactionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	private void updateBalance() {
		getView().getBalance().setText(String.format("$%.2f", account.getBalance()));
	}

}