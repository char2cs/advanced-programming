package mateourrutia.Domain;

import mateourrutia.Domain.Currency.CurrencyInterface;
import mateourrutia.utils.Logger;
import mateourrutia.utils.ObjectWriter;
import mateourrutia.utils.Property;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Account extends ObjectWriter implements Serializable {
	private final 	Long				cbu;
	private 		Double 				balance;
	private 		Client				client;
	private final 	CurrencyInterface 	currency;
	private			Double				currentValue;

	public Account(
			Client 		client,
			double 		balance,
			CurrencyInterface currency
	) {
		super();
		this.cbu 		= 	generateCbu();
		this.client 	= 	client;
		this.balance 	= 	balance;
		this.currency 	= 	currency;
		fetchCurrencyValue();
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

	public CurrencyInterface getCurrency() {
		return currency;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	private Long generateCbu() {
		return ThreadLocalRandom.current().nextLong(
				100000000000L,
				999999999999L + 1
		);
	}

	public void fetchCurrencyValue() {
		String value = Property.get("Currency." + currency.toString());

		if ( value == null )
		{
			this.currentValue = (double) 1;
			return;
		}

		try {
			this.currentValue = Double.parseDouble( value );
		}
		catch (NumberFormatException e) {
			this.currentValue = (double) 1;
			e.printStackTrace();
			Logger.log(Logger.LogLevel.ERROR, "Could not parse value for Currency." + currency.toString());
		}

	}

	public TransactionHistory deposit(double amount) {
		balance += amount;

		return new TransactionHistory(
				TransactionHistory.Type.DEPOSIT,
				TransactionHistory.Status.SUCCESS,
				amount,
				this
		);
	}

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

	public TransactionHistory transfer(
			double amount,
			Account toAccount
	) {
		if ( !toAccount.getCurrency().equals( this.getCurrency() ) )
			return new TransactionHistory(
					TransactionHistory.Type.TRANSFER,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_DIFFERENT_TYPE,
					amount,
					this,
					toAccount
			);

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

	public TransactionHistory convert(
			double amount,
			Account toWallet
	) {
		if ( !toWallet.getClient().getCuit().equals( this.getClient().getCuit() ) )
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_NOT_FROM_SAME_CLIENT,
					amount,
					this,
					toWallet
			);

		if ( toWallet.getCurrency().equals( this.getCurrency() ) )
			return new TransactionHistory(
					TransactionHistory.Type.CONVERT,
					TransactionHistory.Status.ERROR_ACCOUNTS_ARE_SAME_TYPE,
					amount,
					this,
					toWallet
			);

		if ( balance >= amount )
		{
			balance -= amount;
			double convertedAmount = amount * getCurrentValue() / toWallet.getCurrentValue();

			/**
			 * Por alguna razon, el objecto no se actualiza.
			 * Con esto me aseguro de que el objecto es el
			 * original y el que fue modificado.
			 */
			TransactionHistory helper = toWallet.deposit(convertedAmount);

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
				TransactionHistory.Status.ERROR_NOT_ENOUGH_BALANCE,
				amount,
				this,
				toWallet
		);
	}

	@Override
	public String toString() {
		return super.toString() + "Account{" +
				"balance=" + balance +
				"}";
	}
}