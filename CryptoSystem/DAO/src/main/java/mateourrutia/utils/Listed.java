package mateourrutia.utils;

import mateourrutia.Exceptions.ObjectAlreadyExistsException;
import mateourrutia.Exceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Listed<T extends ObjectWriter> implements Serializable {
	List<T> list = new ArrayList<>();

	public Listed() {}

	public Listed(
			List<T> list
	) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * Fuerza a agregar un elemento sin importar su UUID
	 * @param element
	 */
	public void create(T element) {
		list.add(element);
	}

	/**
	 * Agrega un elemento siempre y cuando no este repetido
	 * @param element
	 * @throws IllegalArgumentException Cuando ya existe un elemento con mismo UUID
	 */
	public void add(T element) throws ObjectAlreadyExistsException {
		if ( !has(element.getUuid()) )
			list.add(element);
		else
			throw new ObjectAlreadyExistsException("Element with UUID already exists in the list.");
	}

	/**
	 * Retorna el elemento en el index seleccionado
	 * @param index
	 * @return
	 */
	public T get(int index) {
		return list.get(index);
	}

	/**
	 * Retorna el elemento con el UUID seleccionado
	 * @param uuid UUID del elemento a buscar
	 * @return
	 */
	public T get(UUID uuid) throws ObjectNotFoundException {
		return list.stream()
				.filter(element -> element.getUuid().equals(uuid))
				.findFirst()
				.orElseThrow(() -> new ObjectNotFoundException("Element with UUID not found"));
	}

	/**
	 * Retorna el index del elemento seleccionado
	 * @param element Elemento seleccionado
	 * @return
	 */
	public int get(T element) {
		return list.indexOf(element);
	}

	/**
	 * Elimina el elemento seleccionado
	 * @param element Elemento a remover
	 * @return TRUE - Si se ha logrado
	 */
	public boolean remove(T element) {
		return list.remove(element);
	}

	/**
	 * Elimina el elemento con UUID seleccionado
	 * @param uuid Elemento a remover
	 * @return TRUE - Si se ha logrado
	 */
	public boolean remove(UUID uuid) {
		return list.removeIf( element -> element.getUuid().equals(uuid) );
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public List<T> toList() {
		return new ArrayList<>(list);
	}

	/**
	 * Busca dentro de la lista por un elemento con el mismo UUID
	 * @param uuid Elemento a buscar
	 * @return TRUE - si lo encuentra
	 */
	public boolean has(UUID uuid) {
		return list.stream().anyMatch(element -> element.getUuid().equals(uuid));
	}

	/**
	 * Reemplaza el elemento con el mismo UUID
	 * @param newElement Elemento a reemplazar, con mismo UUID
	 * @throws IllegalArgumentException Si no encuentra el elemento a reemplazar
	 */
	public void set(T newElement) throws ObjectNotFoundException {
		set(newElement.getUuid(), newElement);
	}

	/**
	 * Reemplaza el elemento con el UUID
	 * @param uuid UUID a reemplazar
	 * @param newElement Elemento nuevo
	 * @throws IllegalArgumentException Si no encuentra el elemento a reemplazar
	 */
	public void set(UUID uuid, T newElement) throws ObjectNotFoundException {
		list.set( get( get(uuid) ), newElement );
	}

	/**
	 * Concatena una Listed del mismo tipo con la actual.
	 * @param other La lista a concatenar
	 */
	public void concat(Listed<T> other) throws ObjectAlreadyExistsException {
		for (T element : other.getList())
			try {
				this.add(element);
			}
			catch (ObjectAlreadyExistsException e) {
				throw new ObjectAlreadyExistsException("Element with UUID " + element.getUuid() + " already exists.");
			}

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Listed {\n");

		for (T element : list)
			result.append(element.toString()).append("\n");

		result.append("} Listed END\n");
		return result.toString();
	}
}
