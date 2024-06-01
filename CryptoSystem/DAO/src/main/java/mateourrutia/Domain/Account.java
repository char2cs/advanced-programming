package mateourrutia.Domain;

import mateourrutia.utils.ObjectWriter;
import java.io.Serializable;

public abstract class Account extends ObjectWriter implements Serializable {
	private 	Client		client;
	protected 	double 		balance;

	public Account(
			Client client,
			Integer id,
			double 	balance
	) {
		super();
		this.client 	= 	client;
		this.balance 	= 	balance;
	}

	public Client getClient() {
		return client;
	}

	public double getBalance() {
		return balance;
	}

	public abstract TransactionHistory deposit(
			double amount
	);

	public abstract TransactionHistory withdraw(
			double amount
	);

	public abstract TransactionHistory transfer(
			double amount,
			Account toAccount
	);

	@Override
	public String toString() {
		return super.toString() + "Account{" +
				"balance=" + balance +
				"}";
	}
}