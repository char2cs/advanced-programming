package mateourrutia.controller.Objects;

import mateourrutia.DAO.imp.ClaseDAOImp;
import mateourrutia.controller.TableTypes.ComplexController;
import mateourrutia.controller.TableTypes.GeneralController;
import mateourrutia.controller.TableTypes.SimpleController;
import mateourrutia.controller.TableTypes.TableTypes;
import mateourrutia.domain.Alumno;
import mateourrutia.domain.Clase;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class ClaseController extends ComplexController<Clase, Alumno> {
	ClaseDAOImp claseDAOImp = new ClaseDAOImp();

	public ClaseController() {
		super();

		try {
			this.setControllers(
					createGeneralController(),
					createSimpleController()
			);
		}
		catch ( IllegalAccessException e ) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public GeneralController<Clase> createGeneralController() throws IllegalAccessException {
		Object[] header = {"#", "Nombre", "Pabellon", "Profesor"};

		DefaultTableModel tableModel = new DefaultTableModel(
				TableTypes.convertToTableData( claseDAOImp.getAll() ),
				header
		) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0)
					return Integer.class;

				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 2 || column == 3;
			}
		};

		return new GeneralController<Clase>(tableModel) {
			@Override
			protected Clase onAdd() throws ClassCastException {
				return null;
			}

			@Override
			protected void onUpdate(TableModelEvent e) throws ClassCastException {

			}

			@Override
			protected void onDelete(Integer[] rows, JTable table) throws ClassCastException {

			}
		};
	}

	/**
	 * TODO: 'Alumno' Default Table model here
	 * @return
	 * @throws IllegalAccessException
	 */
	private SimpleController<Alumno> createSimpleController() throws IllegalAccessException {
		Object[] header = {"#", "Nombre", "Pabellon", "Profesor"};

		DefaultTableModel tableModel = new DefaultTableModel(
				TableTypes.convertToTableData( claseDAOImp.getAll() ),
				header
		) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0)
					return Integer.class;

				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 2 || column == 3;
			}
		};

		return new SimpleController<Alumno>(tableModel) {
			@Override
			protected Alumno onAdd() throws ClassCastException {
				return null;
			}

			@Override
			protected void onUpdate(TableModelEvent e) throws ClassCastException {

			}

			@Override
			protected void onDelete(Integer[] rows, JTable table) throws ClassCastException {

			}
		};
	}

}
