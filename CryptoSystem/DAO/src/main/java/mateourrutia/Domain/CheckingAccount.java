package mateourrutia.Domain;

import mateourrutia.Domain.Currency.Currency;

public class CheckingAccount extends Account {
	private double overdraftLimit;

	public CheckingAccount(
			Client 		client,
			double 		balance,
			double 		overdraftLimit,
			Currency 	currency
	) {
		super(client, balance, currency);
		this.overdraftLimit = overdraftLimit;
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	@Override
	public TransactionHistory withdraw(double amount) {
		if (amount <= getBalance() || amount <= getBalance() + overdraftLimit)
		{
			super.withdraw(amount);
			return new TransactionHistory(
					TransactionHistory.Type.WITHDRAW,
					TransactionHistory.Status.SUCCESS,
					amount,
					this
			);
		}

		return new TransactionHistory(
				TransactionHistory.Type.WITHDRAW,
				TransactionHistory.Status.ERROR_OVERDRAFT_ISSUE,
				amount,
				this
		);
	}

	@Override
	public TransactionHistory transfer(double amount, Account toAccount) {
		if (toAccount.getUuid().equals(this.getUuid()))
			return new TransactionHistory(
					TransactionHistory.Type.TRANSFER,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_THE_SAME_ACCOUNT,
					amount,
					this,
					toAccount
			);

		if (!toAccount.getCurrency().equals(this.getCurrency()))
			return new TransactionHistory(
					TransactionHistory.Type.TRANSFER,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_DIFFERENT_TYPE,
					amount,
					this,
					toAccount
			);

		if (amount <= getBalance() || amount <= getBalance() + overdraftLimit)
		{
			super.withdraw(amount);

			TransactionHistory helper = toAccount.deposit(amount);

			return new TransactionHistory(
					TransactionHistory.Type.TRANSFER,
					TransactionHistory.Status.SUCCESS,
					amount,
					this,
					helper.getFromAccount()
			);
		}

		return new TransactionHistory(
				TransactionHistory.Type.TRANSFER,
				TransactionHistory.Status.ERROR_OVERDRAFT_ISSUE,
				amount,
				this,
				toAccount
		);
	}

	@Override
	public TransactionHistory convert(double amount, Account toAccount) {
		if (!toAccount.getClient().getCuit().equals(this.getClient().getCuit()))
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_NOT_FROM_SAME_CLIENT,
					amount,
					this,
					toAccount
			);

		if (toAccount.getCurrency().equals(this.getCurrency()))
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_SAME_TYPE,
					amount,
					this,
					toAccount
			);

		if (amount <= getBalance() || amount <= getBalance() + overdraftLimit)
		{
			super.withdraw(amount);
			double convertedAmount = amount * getCurrentValue() / toAccount.getCurrentValue();

			TransactionHistory helper = toAccount.deposit(convertedAmount);

			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.SUCCESS,
					amount,
					this,
					helper.getFromAccount()
			);
		}

		return new TransactionHistory(
				TransactionHistory.Type.CONVERT,
				TransactionHistory.Status.ERROR_OVERDRAFT_ISSUE,
				amount,
				this,
				toAccount
		);
	}

	@Override
	public String toString() {
		return super.toString() + "CheckingAccount{" +
				"overdraftLimit=" + overdraftLimit +
				"}\n";
	}
}