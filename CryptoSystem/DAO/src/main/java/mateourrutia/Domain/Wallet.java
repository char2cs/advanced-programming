package mateourrutia.Domain;

public class Wallet extends Account {
	private Cryptocurrency cryptocurrency;

	public Wallet(
			Client			client,
			Integer 		id,
			double 			balance,
			Cryptocurrency 	cryptocurrency
	) {
		super(client, id, balance);
		this.cryptocurrency = cryptocurrency;
	}

	public Cryptocurrency getCryptocurrency() {
		return cryptocurrency;
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
		if (balance >= amount && toAccount instanceof Wallet)
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
		else if ( !(toAccount instanceof Wallet) )
			return new TransactionHistory(
				TransactionHistory.Type.TRANSFER,
				TransactionHistory.Status.ERROR_ACCOUNT_IS_NOT_WALLET,
				amount,
				this,
				toAccount
			);

		return new TransactionHistory(
			TransactionHistory.Type.TRANSFER,
			TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE,
			amount,
			this,
			toAccount
		);
	}

	public TransactionHistory convert(
			double amount,
			Wallet toWallet
	) {
		if ( !toWallet.getClient().getCuit().equals( this.getClient().getCuit() ) )
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_WALLETS_ARE_NOT_FROM_SAME_CLIENT,
					amount,
					this,
					toWallet
			);

		if ( balance >= amount )
		{
			balance -= amount;
			double convertedAmount = amount * cryptocurrency.getCurrentValue() / toWallet.getCryptocurrency().getCurrentValue();
			toWallet.deposit(convertedAmount);

			return new TransactionHistory(
				TransactionHistory.Type.CONVERT,
				TransactionHistory.Status.SUCCESS,
				amount,
				this,
				toWallet
			);
		}

		return new TransactionHistory(
			TransactionHistory.Type.CONVERT,
			TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE,
			amount,
			this,
			toWallet
		);
	}
}
