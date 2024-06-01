package mateourrutia.Controller.TableTypes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Arrays;
import java.util.Collections;

import mateourrutia.View.TableTypes.GeneralView;

public abstract class GeneralTable <T> extends TableType<T, GeneralView> {
	public GeneralTable() {}

	public GeneralTable(
			DefaultTableModel model
	) {
		setModel( model );
	}

	protected void setModel(DefaultTableModel model) {
		this.model  = model;
		setView( new GeneralView(this.model) );

		setTableModelListeners();
		setUpListeners();
	}

	// Internal methods
	private void setUpListeners() {
		JTable table = getView().getTable();

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					try {
						Integer[] rowsArray = Arrays.stream( table.getSelectedRows() ).boxed().toArray(Integer[]::new);
						Arrays.sort( rowsArray, Collections.reverseOrder() );

						onDelete( rowsArray, table );
					}
					catch (ClassCastException exception) {
						exception.printStackTrace();
						throw new RuntimeException(exception);
					}
				}
			}
		});

		getView().getDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer[] rowsArray = Arrays.stream( table.getSelectedRows() ).boxed().toArray(Integer[]::new);
					Arrays.sort( rowsArray, Collections.reverseOrder() );

					onDelete( rowsArray, table );
				}
				catch ( ClassCastException exception ) {
					exception.printStackTrace();
					throw new RuntimeException( exception );
				}
			}
		});

		getView().getAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addRow( onAdd() );
				}
				catch ( ClassCastException exception ) {
					exception.printStackTrace();
					throw new RuntimeException( exception );
				}
			}
		});
	}
}