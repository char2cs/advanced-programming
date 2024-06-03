package mateourrutia.Domain;

import mateourrutia.utils.ObjectWriter;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Account extends ObjectWriter implements Serializable {
	private final 	Long	cbu;
	protected 		double 	balance;
	private 		Client	client;

	public Account(
			Client client,
			double 	balance
	) {
		super();
		this.cbu 		= 	generateCbu();
		this.client 	= 	client;
		this.balance 	= 	balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getCbu() {
		return cbu;
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

	public abstract TransactionHistory convert(
		double amount,
		Account toAccount
	);

	@Override
	public String toString() {
		return super.toString() + "Account{" +
				"balance=" + balance +
				"}";
	}

	private Long generateCbu() {
		return ThreadLocalRandom.current().nextLong(
				100000000000L,
				999999999999L + 1
		);
	}
}