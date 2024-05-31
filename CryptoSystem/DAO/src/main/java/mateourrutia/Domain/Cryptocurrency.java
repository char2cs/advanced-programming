package mateourrutia.Domain;

import mateourrutia.helper.Logger;
import mateourrutia.helper.Property;

public class Cryptocurrency  {
	private String name;
	private double currentValue;

	public Cryptocurrency(
			String name
	) {
		this.name = name;
		fetchCurrentValue();
	}

	public String getName() {
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
}