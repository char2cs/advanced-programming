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
		if (balance + overdraftLimit >= amount)
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
		if (balance + overdraftLimit >= amount)
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
	public String toString() {
		return super.toString() + "CheckingAccount{" +
				"overdraftLimit=" + overdraftLimit +
				"}\n";
	}
}