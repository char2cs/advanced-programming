package mateourrutia.Domain.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Este Enum esta destinado solo para unir ambos
 * enums en uno, y agregar la opcion de All.
 * Su uso es principalmente para los filtros de
 * TransactionHistory.
 */
public enum AllCurrency implements CurrencyInterface {
	ALL();

	public static List<CurrencyInterface> getAlL() {
		List<CurrencyInterface> combinedValues = new ArrayList<>();

		combinedValues.add(AllCurrency.ALL);

		for (Currency enum1 : Currency.values())
			combinedValues.add(enum1);

		for (CryptoCurrency enum2 : CryptoCurrency.values())
			combinedValues.add(enum2);

		return combinedValues;
	}
}
