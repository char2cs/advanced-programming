package mateourrutia.domain;

public class SavingsAccount extends Account {
	private Integer cbu;
	private Integer cuit;

	public SavingsAccount(
			Client 	client,
			Integer id,
			double 	balance,
			Integer cbu,
			Integer cuit
	) {
		super(client, id, balance);
		this.cbu 	= cbu;
		this.cuit 	= cuit;
	}

	public Integer getCbu() {
		return cbu;
	}

	public Integer getCuit() {
		return cuit;
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
}
