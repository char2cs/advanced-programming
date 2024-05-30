package mateourrutia.helper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import mateourrutia.helper.Property;

public abstract class Logger {

	public enum LogLevel {
		ERROR,
		INFO,
		WARN,
		FATAL,
		SUCCESS
	}

	public static boolean log(LogLevel level, String message) {
		String Path = Property.get("logger.folder");
		String PathConcat = Path != null && !Path.isEmpty() ?
				Path.concat("/") : "";

		try {
			File logFile = new File(
					PathConcat.concat( generateLogFileName() )
			);

			if ( !logFile.exists() && Path != null )
			{
				logFile.getParentFile().mkdirs();
				logFile.createNewFile();
			}
			else if ( !logFile.exists() && Path == null )
				logFile.createNewFile();

			FileWriter writer = new FileWriter(logFile, true);
			writer.write( formatLogEntry(level, message) );
			writer.write( System.lineSeparator() );
			writer.close();

			return true;
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("UNFORESEEN CONSEQUENCES: Folder or file not found!, try creating the config folder");
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("UNFORESEEN CONSEQUENCES: NullPointerException...");
		}

		return false;
	}

	private static String generateLogFileName() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format( new Date() ) + ".log";
	}

	private static String formatLogEntry(LogLevel level, String message) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String timestamp = timeFormat.format(new Date());
		return "[" + timestamp + "] [" + level.name() + "] - " + message;
	}

}
