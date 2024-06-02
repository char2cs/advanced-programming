package mateourrutia.utils.persistance;

import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Exceptions.OperationFailedException;
import mateourrutia.utils.Logger;
import mateourrutia.utils.ObjectWriter;
import mateourrutia.utils.Property;

import java.io.Serializable;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Writers contiene todos los metodos que una clase almacenadora de objetos requiere, incluyendo
 * metodos CRUD.
 * @param <T>
 */
public abstract class Writers<T extends ObjectWriter & Serializable> {
	private final String className;
	private final String filePath;

	public Writers(
			String className,
			String fileExtension
	) {
		String Path = Property.get("data.folder");
		String PathConcat =  Path != null && !Path.isEmpty() ?
				Path.concat("/") : "";

		this.filePath = PathConcat.concat(
				className.concat(".").concat(fileExtension)
		);
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public String getFilePath() {
		return filePath;
	}

	protected abstract List<T> readFile();
	protected abstract boolean saveFile( List<T> objects );

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
