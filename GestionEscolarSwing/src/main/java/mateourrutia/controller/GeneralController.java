package mateourrutia.controller;

import mateourrutia.view.General.GeneralView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public abstract class GeneralController<T> {
    private DefaultTableModel model;
    private GeneralView view;

    public GeneralController() {}

    public GeneralController(
            DefaultTableModel model
    ) {
        setModel( model );
    }

    public DefaultTableModel getModel() {
        return model;
    }

    protected void setModel(DefaultTableModel model) {
        this.model  = model;
        this.view   = new GeneralView( this.model );

        setTableModelListeners();
        setUpListeners();
    }

    public GeneralView getView() {
        return view;
    }

    // Default Table Model things
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

    // Children's helpers
    /**
     * Parse or convert any list of object T into an 2D Object array.
     * @param dataList
     * @return
     * @throws IllegalAccessException
     */
    protected Object[][] convertToTableData(
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
    private Object[] parseObjectToArray(
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
            int[] rows,
            JTable table
    ) throws ClassCastException;

    // Internal methods
    private void setTableModelListeners() {
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

    private void setUpListeners() {
        JTable table = view.getTable();

        view.getDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onDelete( table.getSelectedRows(), table );
                }
                catch ( ClassCastException exception ) {
                    exception.printStackTrace();
                    throw new RuntimeException( exception );
                }
            }
        });
    }
}
