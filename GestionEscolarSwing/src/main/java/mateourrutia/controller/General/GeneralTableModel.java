package mateourrutia.controller.General;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class GeneralTableModel<T> extends AbstractTableModel {
    private final List<T> data;
    private final Field[] fields;
    private final String[] columnNames;

    public GeneralTableModel(List<T> data, Class<T> clazz) {
        this.data = data;
        this.fields = clazz.getDeclaredFields();
        this.columnNames = new String[fields.length];

        for (int i = 0; i < fields.length; i++)
        {
            fields[i].setAccessible(true);
            columnNames[i] = fields[i].getName();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T obj = data.get(rowIndex);
        try {
            return fields[columnIndex].get(obj);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void addRow(T obj) {
        data.add(obj);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void updateRow(int rowIndex, T obj) {
        data.set(rowIndex, obj);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
