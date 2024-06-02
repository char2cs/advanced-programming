package mateourrutia.utils.persistance;

import java.io.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import mateourrutia.utils.Logger;
import mateourrutia.utils.ObjectWriter;

/**
 * En este caso, usamos Base64 para almacenar datos utilizando Strings.
 * @param <T>
 */
public class StringWriter<T extends ObjectWriter & Serializable> extends Writers<T> {

	public StringWriter(String className) {
		super( className, "B64" );
	}

	protected List<T> readFile() {
		List<T> contents = new ArrayList<>();
		try ( BufferedReader br = new BufferedReader( new FileReader( getFilePath() ) ) ) {
			String line;

			while ((line = br.readLine()) != null)
			{
				T object = parseObject(line);
				if (object != null)
					contents.add(object);
			}
		}
		catch (FileNotFoundException e) {
			Logger.log(Logger.LogLevel.FATAL, "@ '" + getClassName() + "' File not found: " + e.getMessage());
		}
		catch (IOException e) {
			Logger.log(Logger.LogLevel.FATAL, "@ '" + getClassName() + "' IOException: " + e.getMessage());
			e.printStackTrace();
		}

		return contents;
	}

	protected boolean saveFile(List<T> objects) {
		try ( BufferedWriter bw = new BufferedWriter( new FileWriter(getFilePath()) ) ) {
			for (T object : objects)
			{
				bw.write(serializeObject(object));
				bw.newLine();
			}

			Logger.log(Logger.LogLevel.INFO, "@ '" + getClassName() + "' Object saved");
		}
		catch (IOException e) {
			Logger.log(Logger.LogLevel.FATAL, "@ '" + getClassName() + "' IOException: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private String serializeObject(T object) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(object);
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		}
		catch (IOException e) {
			e.printStackTrace();
			Logger.log(Logger.LogLevel.FATAL, "@ '" + getClassName() + "' Serialization Error using StringWriter: " + e.getMessage());
			return null;
		}
	}

	private T parseObject(String str) {
		byte[] data = Base64.getDecoder().decode(str);
		try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
			 ObjectInputStream ois = new ObjectInputStream(bais)) {
			return (T) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			Logger.log(Logger.LogLevel.FATAL, "@ '" + getClassName() + "' Parse Error using StringWriter: " + e.getMessage());
			return null;
		}
	}

}
