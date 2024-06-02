package mateourrutia.utils;

import java.io.IOException;
import java.io.FileReader;
import java.util.Properties;

/**
 * De manera estatica, devuelve una setting especifica dentro del archivo env.properties.
 */
public abstract class Property {
	public static String get(
			String property
	) {
		Properties properties = new Properties();

		try {
			properties.load(new FileReader("env.properties"));
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return properties.getProperty( property );
	}
}
