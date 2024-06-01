package mateourrutia.Domain;

public class SavingsAccount extends Account {

	public SavingsAccount(
			Client 	client,
			double 	balance
	) {
		super(client, balance);
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
		if (balance >= amount)
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
				TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE,
				amount,
				this
		);
	}

	@Override
	public TransactionHistory transfer(
			double amount,
			Account toAccount
	) {
		if (balance >= amount)
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
				TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE,
				amount,
				this,
				toAccount
		);
	}

	@Override
	public String toString() {
		return super.toString() + "SavingsAccount{" +
				"cbu=" + getCbu() +
				"}\n";
	}
}
