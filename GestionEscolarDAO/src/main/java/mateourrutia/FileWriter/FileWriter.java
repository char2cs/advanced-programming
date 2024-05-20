package mateourrutia.FileWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import mateourrutia.helper.Property;

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
	}

	public void add(T object) {
		if ( this.get( object.getId() ) != null )
			return;

		this.create( object );
	}

	public T get(Integer id) {
		List<T> objects = this.readFile();

		if ( objects != null && !objects.isEmpty() )
			for ( T object : objects )
				if ( object.getId().equals(id) )
					return object;

		return null;
	}

	public boolean update(T object) {
		List<T> objects = readFile();

		if (objects != null && !objects.isEmpty())
			for (int i = 0; i < objects.size(); i++)
				if ( objects.get(i).getId().equals(object.getId()) )
				{
					objects.set(i, object);
					saveFile(objects);
					return true;
				}

		return false;
	}

	public boolean delete(Integer id) {
		List<T> objects = readFile();

		if (objects != null && !objects.isEmpty())
			for (int i = 0; i < objects.size(); i++)
				if ( objects.get(i).getId().equals(id) )
				{
					objects.remove(i);
					saveFile(objects);
					return true;
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
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return contents;
	}

	// Method to write an ArrayList to the file
	private boolean fileAddContents(ArrayList<T> newContents) {
		try (
				ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(filePath) )
		) {
			oos.writeObject( newContents );
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean saveFile( List<T> objects ) {
		ArrayList<T> newObjects = new ArrayList<>( objects );
		return this.fileAddContents( newObjects );
	}

}
