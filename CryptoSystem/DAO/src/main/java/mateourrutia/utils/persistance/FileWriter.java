package mateourrutia.utils.persistance;

import java.io.*;
import java.util.*;

import mateourrutia.utils.Logger;
import mateourrutia.utils.ObjectWriter;

/**
 * En este caso, utilizamos Bytes para almacenar los objectos, utiliznado el ObjectInputStream.
 *
 * Todos los archivos Base64 terminan con la extension '.dat'
 * @param <T>
 */
public class FileWriter<T extends ObjectWriter & Serializable> extends Writers<T> {

	public FileWriter(String className) {
		super(className, "dat");
	}

	@SuppressWarnings("unchecked")
	protected ArrayList<T> readFile() {
		ArrayList<T> contents = new ArrayList<>();

		try (
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream(getFilePath()) )
		) {
			contents = (ArrayList<T>) ois.readObject();
		}
		catch (FileNotFoundException e) {
			System.out.println("[FileWriter] @ '" + getClassName() + "' File not found: " + e.getMessage());
			Logger.log(
					Logger.LogLevel.FATAL,
					"@ '" + getClassName() + "' File not found: " + e.getMessage()
			);
		}
		catch (IOException | ClassNotFoundException e) {
			Logger.log(
					Logger.LogLevel.FATAL,
					"@ '" + getClassName() + "' IOException | ClassNotFoundException: " + e.getMessage()
			);
			e.printStackTrace();
		}

		return contents;
	}

	protected boolean saveFile( List<T> objects ) {
		ArrayList<T> newObjects = new ArrayList<>( objects );

		try (
				ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(getFilePath()) )
		) {
			oos.writeObject( newObjects );

			Logger.log(
					Logger.LogLevel.INFO,
					"@ '" + getClassName() + "' Object saved"
			);
		}
		catch (IOException e) {
			Logger.log(
					Logger.LogLevel.FATAL,
					"@ '" + getClassName() + "' IOException: " + e.getMessage()
			);
			e.printStackTrace();
			return false;
		}

		return true;
	}
}

