package mateourrutia.controller.TableTypes;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public abstract class TableTypes<T> {
	protected DefaultTableModel model;

	public DefaultTableModel getModel() {
		return model;
	}

	protected abstract void setModel(DefaultTableModel model);

	/**
	 * Will add the object to the table.
	 * @param obj
	 */
	public void addRow(T obj) {
		try {
			model.addRow( parseObjectToArray(obj) );
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Will remove row from table.
	 * @param rowIndex
	 */
	public void removeRow(int rowIndex) {
		model.removeRow( rowIndex );
	}

	/**
	 * On add button event, will fire up this event.
	 * @return
	 * @throws ClassCastException
	 */
	protected abstract T onAdd() throws ClassCastException;

	/**
	 * On row update, this function will be called.
	 * @param e TableModelEvent - Event
	 * @throws ClassCastException
	 */
	protected abstract void onUpdate(
			TableModelEvent e
	) throws ClassCastException;

	/**
	 * When user press onDelete btn, this event will fire.
	 * ! ROWS ARE NOT IDs.
	 * @param rows
	 * @param table
	 */
	protected abstract void onDelete(
			Integer[] rows,
			JTable table
	) throws ClassCastException;

	// ? Methods
	protected void setTableModelListeners() {
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				try {
					if (e.getType() == TableModelEvent.UPDATE)
						onUpdate(e);
				}
				catch ( ClassCastException exception ) {
					exception.printStackTrace();
				}
			}
		});
	}

	// ? Children's helpers
	/**
	 * Parse or convert any list of object T into an 2D Object array.
	 * @param dataList
	 * @return
	 * @throws IllegalAccessException
	 */
	public static <T> Object[][] convertToTableData(
			List<T> dataList
	) throws IllegalAccessException {
		if ( dataList.isEmpty() ) // Descartamos que la lista este vacia
			return new Object[0][0];

		int columnCount = dataList.get(0).getClass().getDeclaredFields().length; // Conseguimos la cantidad de campos del objetod
		Object[][] data = new Object[dataList.size()][columnCount]; // Creamos el array 2D de objetos

		for ( int i = 0; i < dataList.size(); i++ )
		{
			T obj           = dataList.get(i);
			Object[] row    = parseObjectToArray(obj); // Parseamos el objeto de tipo T a tipo Object[]
			data[i]         = row;
		}

		return data;
	}

	/**
	 * Will parse or convert any object of type T into an Object array.
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 */
	public static <T> Object[] parseObjectToArray(
			T obj
	) throws IllegalAccessException {
		Class<?>    clazz   = obj.getClass();
		Field[]     fields  = clazz.getDeclaredFields();
		Object[]    row     = new Object[fields.length];

		for (int i = 0; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			row[i]  = fields[i].get(obj);
		}

		return row;
	}
}
