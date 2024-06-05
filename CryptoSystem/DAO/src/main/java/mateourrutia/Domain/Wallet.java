package mateourrutia.Domain;

import mateourrutia.Domain.Currency.CryptoCurrency;

public class Wallet extends Account {
	public Wallet(
			Client			client,
			double 			balance,
			CryptoCurrency 	currency
	) {
		super(client, balance, currency);
	}
}
