package mateourrutia.utils;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;

public class ListWriter<T extends ObjectWriter> {
	private final String className;
	private final String filePath;

	public ListWriter(
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
			System.out.println("[ListWriter] @ '" + this.className + "' File not found: " + e.getMessage());
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

	// General CRUD methods
	public void create(T object) {
		List<T> objects = this.readFile();

		if ( objects == null )
			objects = new ArrayList<>();

		objects.add(object);
		saveFile(objects);

		Logger.log(
				Logger.LogLevel.SUCCESS,
				"@ '" + this.className + "' Object with id: '" + object.getUuid() + "' added."
		);
	}

	public void add(T object) throws ObjectAlreadyExistsException {
		try {
			this.get( object.getUuid() );
			Logger.log(
					Logger.LogLevel.WARN,
					"@ '" + this.className + "' Tried to add repeated object with id '" + object.getUuid() + "'"
			);
			throw new ObjectAlreadyExistsException("Object with id '" + object.getUuid() + "' already exists");
		}
		catch (ObjectNotFoundException e) {
			this.create( object );
		}
	}

	public T get(UUID uuid) throws ObjectNotFoundException {
		List<T> objects = this.readFile();

		if (objects != null && !objects.isEmpty())
		{
			Optional<T> result = objects.stream()
					.filter(object -> object.getUuid().equals(uuid))
					.findFirst();

			return result.orElseThrow( () -> new ObjectNotFoundException("Object with id '" + uuid + "' not found.") );
		}

		throw new ObjectNotFoundException("Object with id '" + uuid + "' not found.");
	}

	public boolean update(T object) throws ObjectNotFoundException, OperationFailedException {
		List<T> objects = readFile();

		if (objects != null && !objects.isEmpty())
		{
			OptionalInt index = IntStream.range(0, objects.size())
					.filter( i -> objects.get(i).getUuid().equals(object.getUuid()) )
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
						"@ '" + this.className + "' Updated object with id '" + object.getUuid() + "'"
				);
				return true;
			}

			throw new ObjectNotFoundException("Object with id '" + object.getUuid() + "' not found.");
		}

		throw new OperationFailedException("Update couldn't be done, on object '" + object.getUuid() + "'.");
	}

	public boolean delete(UUID uuid) throws ObjectNotFoundException, OperationFailedException {
		List<T> objects = readFile();

		if (objects != null && !objects.isEmpty())
		{
			OptionalInt index = IntStream.range(0, objects.size())
					.filter( i -> objects.get(i).getUuid().equals( uuid ) )
					.findFirst();

			if ( index.isPresent() )
			{
				objects.remove( index.getAsInt() );
				saveFile(objects);

				Logger.log(
						Logger.LogLevel.SUCCESS,
						"@ '" + this.className + "' Removed object with id '" + uuid + "'"
				);

				return true;
			}

			throw new ObjectNotFoundException("Object with id '" + uuid + "' not found.");
		}

		throw new OperationFailedException("Delete couldn't be done, on object '" + uuid + "'.");
	}

	public List<T> getAll() {
		List<T> objects = this.readFile();

		if ( objects != null && !objects.isEmpty() )
			return objects;

		return new ArrayList<>();
	}

}

