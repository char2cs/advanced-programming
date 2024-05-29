package mateourrutia.render.ButtonTable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	private JButton button;
	private JTable 	table;
	private int 	row;
	private int 	column;
	private boolean isPushed;

	public ButtonEditor() {
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(this);
	}

	public abstract String getLabel(Object object);

	public abstract JPanel onClick(Object object);

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table 	= table;
		this.row 	= row;
		this.column = column;

		button.setText( getLabel(value) );
		isPushed = true;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		return button.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( isPushed )
			onClick( table.getValueAt(row, column) );

		isPushed = false;
		fireEditingStopped();
	}
}
