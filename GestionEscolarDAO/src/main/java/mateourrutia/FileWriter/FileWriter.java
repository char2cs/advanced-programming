package mateourrutia.FileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import mateourrutia.helper.Property;
import mateourrutia.helper.Logger;

public class FileWriter<T extends ObjectWriter> {
	private final String className;
	private final String filePath;

	public FileWriter(
			String className
	) {
		String Path = Property.get("data.folder");
		String PathConcat =  Path != null && !Path.isEmpty() ?
				Path.concat("/") : "";

		this.filePath = PathConcat.concat(
				className.concat(".dat")
		);
		this.className = className;
	}

	// General CRUD methods
	public void create(T object) {
		List<T> objects = this.readFile();

		if ( objects == null )
			objects = new ArrayList<>();

		objects.add(object);
		saveFile(objects);

		Logger.log(
			Logger.LogLevel.SUCCESS,
			"@ '" + this.className + "' Object with id: '" + object.getId() + "' added."
		);
	}

	public void add(T object) {
		if ( this.get( object.getId() ) != null )
		{
			Logger.log(
				Logger.LogLevel.WARN,
				"@ '" + this.className + "' Tried to add repeated object with id '" + object.getId() + "'"
			);
			return;
		}

		this.create( object );
	}

	public T get(Integer id) {
		List<T> objects = this.readFile();

		if (objects != null && !objects.isEmpty())
		{
			Optional<T> result = objects.stream()
				.filter(object -> object.getId().equals(id))
					.findFirst();

			return result.orElse(null);
		}

		return null;
	}

	public boolean update(T object) {
		List<T> objects = readFile();

		if (objects != null && !objects.isEmpty())
		{
			OptionalInt index = IntStream.range(0, objects.size())
				.filter( i -> objects.get(i).getId().equals(object.getId()) )
					.findFirst();

			if ( index.isPresent() )
			{
				objects.set(
						index.getAsInt(),
						object
				);
				saveFile(objects);

				Logger.log(
						Logger.LogLevel.SUCCESS,
						"@ '" + this.className + "' Updated object with id '" + object.getId() + "'"
				);
				return true;
			}
		}

		return false;
	}

	public boolean delete(Integer id) {
		List<T> objects = readFile();

		if (objects != null && !objects.isEmpty())
		{
			OptionalInt index = IntStream.range(0, objects.size())
					.filter( i -> objects.get(i).getId().equals( id ) )
					.findFirst();

			if ( index.isPresent() )
			{
				objects.remove( index.getAsInt() );
				saveFile(objects);

				Logger.log(
						Logger.LogLevel.SUCCESS,
						"@ '" + this.className + "' Removed object with id '" + id + "'"
				);
				return true;
			}
		}

		return false;
	}

	public List<T> getAll() {
		List<T> objects = this.readFile();

		if ( objects != null && !objects.isEmpty() )
			return objects;

		return null;
	}

	// File interacting methods...
	@SuppressWarnings("unchecked")
	private ArrayList<T> readFile() {
		ArrayList<T> contents = new ArrayList<>();

		try (
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream(filePath) )
		) {
			contents = (ArrayList<T>) ois.readObject();
		}
		catch (FileNotFoundException e) {
			System.out.println("[FileWriter] @ '" + this.className + "' File not found: " + e.getMessage());
			Logger.log(
				Logger.LogLevel.FATAL,
				"@ '" + this.className + "' File not found: " + e.getMessage()
			);
		}
		catch (IOException | ClassNotFoundException e) {
			Logger.log(
				Logger.LogLevel.FATAL,
				"@ '" + this.className + "' IOException | ClassNotFoundException: " + e.getMessage()
			);
			e.printStackTrace();
		}

		return contents;
	}

	private boolean saveFile( List<T> objects ) {
		ArrayList<T> newObjects = new ArrayList<>( objects );

		try (
				ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(filePath) )
		) {
			oos.writeObject( newObjects );

			Logger.log(
				Logger.LogLevel.INFO,
				"@ '" + this.className + "' Object saved"
			);
		}
		catch (IOException e) {
			Logger.log(
				Logger.LogLevel.FATAL,
				"@ '" + this.className + "' IOException: " + e.getMessage()
			);
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
