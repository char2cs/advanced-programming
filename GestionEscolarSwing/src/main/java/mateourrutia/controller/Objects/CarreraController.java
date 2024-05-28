package mateourrutia.controller.Objects;

import mateourrutia.DAO.imp.CarreraDAOImp;
import mateourrutia.controller.GeneralController;
import mateourrutia.domain.Carrera;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarreraController extends GeneralController<Carrera> {
	private final CarreraDAOImp carreraDAOImp = new CarreraDAOImp();

	public CarreraController() {
		super();

		try {
			Object[] header = {"#", "Nombre"};

			DefaultTableModel tableModel = new DefaultTableModel(
					convertToTableData( carreraDAOImp.getAll() ),
					header
			) {
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					if ( columnIndex == 0 )
						return Integer.class;

					return super.getColumnClass(columnIndex);
				}

				@Override
				public boolean isCellEditable(int row, int column) {
					return column == 1;
				}
			};

			this.setModel(tableModel);
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Carrera onAdd() throws ClassCastException {
		List<Carrera> carreras = carreraDAOImp.getAll();
		Carrera carrera;

		if ( !carreras.isEmpty() )
			carrera = new Carrera(
					carreras.get(carreras.size() - 1).getId() + 1,
					""
			);
		else
			carrera = new Carrera(
					0,
					""
			);

		carreraDAOImp.add( carrera );

		return carrera;
	}

	@Override
	protected void onUpdate(
			TableModelEvent e
	) throws ClassCastException {
		DefaultTableModel Model = this.getModel();
		int row 				= e.getFirstRow();
		int column 				= e.getColumn();

		Object 	newValue 	= Model.getValueAt(row, column);
		Carrera carrera 	= carreraDAOImp.get(
				(int) Model.getValueAt(row, 0)
		);

		switch (column) {
			case 0:
				break;

			case 1:
				carrera.setName( (String) newValue );
				break;
		}

		carreraDAOImp.update( carrera );
	}

	@Override
	protected void onDelete(
			Integer[] rows,
			JTable table
	) throws ClassCastException {
		for (int row : rows)
		{
			carreraDAOImp.delete( (int) table.getValueAt(row, 0) );
			this.removeRow( row );
		}
	}
}
