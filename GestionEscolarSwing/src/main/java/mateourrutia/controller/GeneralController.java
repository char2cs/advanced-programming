package mateourrutia.controller;

import mateourrutia.view.General.GeneralView;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class GeneralController<T> {
    private DefaultTableModel model;
    private GeneralView view;

    public GeneralController() {}

    public GeneralController(
            DefaultTableModel model
    ) {
        setModel( model );
    }

//    public void addRow(T obj) {
//        model.addRow(obj);
//    }

//    public void removeRow(int rowIndex) {
//        model.removeRow(rowIndex);
//    }

//    public void updateRow(int rowIndex, T obj) {
//        model.updateRow(rowIndex, obj);
//    }

    public DefaultTableModel getModel() {
        return model;
    }

    protected void setModel(DefaultTableModel model) {
        this.model  = model;
        this.view   = new GeneralView( this.model );
    }

    public GeneralView getView() {
        return view;
    }

    // Helper for children...
    protected Object[][] convertToTableData(
            List<T> dataList
    ) throws IllegalAccessException {
        if ( dataList.isEmpty() ) // Descartamos que la lista este vacia
            return new Object[0][0];

        int columnCount = dataList.get(0).getClass().getDeclaredFields().length; // Conseguimos la cantidad de campos del objeto

        Object[][] data = new Object[dataList.size()][columnCount]; // Creamos el array 2D de objetos

        for ( int i = 0; i < dataList.size(); i++ )
        {
            T obj = dataList.get(i);
            Object[] row = parseObjectToArray(obj); // Parseamos el objeto de tipo T a tipo Object[]
            data[i] = row;
        }

        return data;
    }

    private Object[] parseObjectToArray(
            T obj
    ) throws IllegalAccessException {
        Class<?>    clazz   = obj.getClass();
        Field[]     fields  = clazz.getDeclaredFields();
        Object[]    row     = new Object[fields.length];

        for (int i = 0; i < fields.length; i++)
        {
            fields[i].setAccessible(true);
            row[i] = fields[i].get(obj);
        }

        return row;
    }
}
