package mateourrutia.domain;

public abstract class Account {
	private 	Client		client;
	private 	Integer 	id;
	protected 	double 		balance;

	public Account(
			Client client,
			Integer id,
			double 	balance
	) {
		this.client 	= 	client;
		this.id 		= 	id;
		this.balance 	= 	balance;
	}

	public Client getClient() {
		return client;
	}

	public Integer getId() {
		return id;
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
}