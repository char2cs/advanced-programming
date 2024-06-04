package mateourrutia.Domain;

public class CheckingAccount extends Account {
	private double overdraftLimit;

	public CheckingAccount(
			Client 	client,
			double 	balance,
			double 	overdraftLimit
	) {
		super(client, balance);
		this.overdraftLimit = overdraftLimit;
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	@Override
	public TransactionHistory deposit(double amount) {
		balance += amount;

		return new TransactionHistory(
				TransactionHistory.Type.DEPOSIT,
				TransactionHistory.Status.SUCCESS,
				amount,
				this
		);
	}

	@Override
	public TransactionHistory withdraw(double amount) {
		if (amount <= balance || amount <= balance + overdraftLimit)
		{
			balance -= amount;
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

		if (toAccount instanceof Wallet)
			return new TransactionHistory(
					TransactionHistory.Type.TRANSFER,
					TransactionHistory.Status.ERROR_ACCOUNT_IS_WALLET,
					amount,
					this,
					toAccount
			);

		if (amount <= balance || amount <= balance + overdraftLimit)
		{
			balance -= amount;
			toAccount.deposit(amount);
			return new TransactionHistory(
					TransactionHistory.Type.TRANSFER,
					TransactionHistory.Status.SUCCESS,
					amount,
					this,
					toAccount
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
		if (!(toAccount instanceof Wallet))
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_ACCOUNT_IS_NOT_WALLET,
					amount,
					this,
					toAccount
			);

		if (!toAccount.getClient().getCuit().equals(this.getClient().getCuit()))
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_WALLETS_ARE_NOT_FROM_SAME_CLIENT,
					amount,
					this,
					toAccount
			);

		if (amount <= balance || amount <= balance + overdraftLimit)
		{
			balance -= amount;
			double convertedAmount = amount / ((Wallet) toAccount).getCryptocurrency().getCurrentValue();
			toAccount.deposit(convertedAmount);

			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.SUCCESS,
					convertedAmount,
					this,
					toAccount
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