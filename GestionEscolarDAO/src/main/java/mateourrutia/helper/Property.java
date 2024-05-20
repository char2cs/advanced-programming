package mateourrutia.helper;

import java.io.IOException;
import java.io.FileReader;
import java.util.Properties;

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
