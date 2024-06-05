package mateourrutia.Domain;

import mateourrutia.Domain.Currency.Currency;

public class SavingsAccount extends Account {
	private Long cuit;

	public SavingsAccount(
			Client 		client,
			double 		balance,
			Currency 	currency
	) {
		super(client, balance, currency);
		this.cuit = client.getCuit();
	}

	public Long getCuit() {
		return cuit;
	}

	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}

	@Override
	public String toString() {
		return super.toString() + "SavingsAccount{" +
				"cbu=" + getCbu() +
				"}\n";
	}
}
