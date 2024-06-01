package mateourrutia.Controller.TableTypes;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

public abstract class TableType<T> {
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
	 * Parse or convert any list of object T into a 2D Object array.
	 *
	 * @param dataList The list of objects to be converted.
	 * @return A 2D Object array representing the data.
	 * @throws IllegalAccessException If the fields of the objects are not accessible.
	 */
	public static <T> Object[][] convertToTableData(List<T> dataList) throws IllegalAccessException {
		if (dataList.isEmpty())
			return new Object[0][0];

		List<Field> fields 	= getAllFields(dataList.get(0).getClass());
		int columnCount 	= fields.size();
		Object[][] data 	= new Object[dataList.size()][columnCount];

		for (int i = 0; i < dataList.size(); i++)
		{
			T obj 			= dataList.get(i);
			Object[] row 	= parseObjectToArray(obj, fields);
			data[i] 		= row;
		}

		return data;
	}

	private static List<Field> getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		while (clazz != null)
		{
			for (Field field : clazz.getDeclaredFields())
				fields.add(field);

			clazz = clazz.getSuperclass();
		}

		return fields;
	}

	public static <T> Object[] parseObjectToArray(
			T obj,
			List<Field> fields
	) throws IllegalAccessException {
		Object[] row = new Object[fields.size()];

		for (int j = 0; j < fields.size(); j++)
		{
			Field field = fields.get(j);
			field.setAccessible(true);
			row[j] = field.get(obj);
		}

		return row;
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