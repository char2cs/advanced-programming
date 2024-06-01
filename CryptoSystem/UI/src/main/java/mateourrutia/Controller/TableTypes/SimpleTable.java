package mateourrutia.Controller.TableTypes;

import mateourrutia.View.TableTypes.SimpleView;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public abstract class SimpleTable<T> extends TableType<T, SimpleView> {
	public SimpleTable() {}

	public SimpleTable(
			DefaultTableModel model
	) {
		setModel( model );
	}

	protected void setModel(DefaultTableModel model) {
		this.model  = model;
		setView( new SimpleView(this.model) );

		setTableModelListeners();
		setUpListeners();
	}

	@Override
	protected T onAdd() throws ClassCastException {
		return null;
	}

	@Override
	protected void onUpdate(TableModelEvent e) throws ClassCastException {

	}

	@Override
	protected void onDelete(Integer[] rows, JTable table) throws ClassCastException {

	}

	// Internal methods
	private void setUpListeners() {}
}