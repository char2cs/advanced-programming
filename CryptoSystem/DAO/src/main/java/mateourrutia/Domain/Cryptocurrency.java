package mateourrutia.Domain;

import mateourrutia.utils.Logger;
import mateourrutia.utils.Property;

import java.io.Serializable;

public class Cryptocurrency implements Serializable {
	private final Currency name;
	private double currentValue;

	public Cryptocurrency(
			Currency name
	) {
		this.name = name;
		fetchCurrentValue();
	}

	public Currency getName() {
		return name;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public void fetchCurrentValue() {
		String value = Property.get("Cryptocurrency." + name);

		if ( value == null )
		{
			this.currentValue = 1;
			return;
		}

		try {
			this.currentValue = Double.parseDouble( value );
		}
		catch (NumberFormatException e) {
			this.currentValue = 1;
			e.printStackTrace();
			Logger.log(Logger.LogLevel.ERROR, "Could not parse value for Cryptocurrency." + name);
		}

	}

	@Override
	public String toString() {
		return "Cryptocurrency{" +
				"name='" + name + '\'' +
				", currentValue=" + currentValue +
				'}';
	}
}