package mateourrutia.domain;

public abstract class Account {
	private 	Integer 	id;
	protected 	double 		balance;

	public Account() {}

	public Account(
			Integer id,
			double 	balance
	) {
		this.id 		= 	id;
		this.balance 	= 	balance;
	}

	public Integer getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public abstract void deposit(
			double amount
	);

	public abstract void transfer(
			double amount,
			Account toAccount
	);
}