package mateourrutia.controller.Objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import mateourrutia.DAO.imp.ClaseDAOImp;
import mateourrutia.controller.DefaultDialogController;
import mateourrutia.controller.TableTypes.ComplexController;
import mateourrutia.controller.TableTypes.GeneralController;
import mateourrutia.controller.TableTypes.TableTypes;
import mateourrutia.domain.Alumno;
import mateourrutia.domain.Clase;

import mateourrutia.domain.Pabellon;
import mateourrutia.render.ButtonTable.ButtonTableRenderer;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class ClaseController extends ComplexController<Clase, Alumno> {
	ClaseDAOImp claseDAOImp = new ClaseDAOImp();

	public ClaseController(Window window) {
		super(window);

		try {
			this.setControllers(
					createGeneralController( claseDAOImp.getAll() ),
					createSimpleController( new ArrayList<>() )
			);
		}
		catch ( IllegalAccessException e ) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static DefaultTableModel generalTableModel (
			List<Clase> objects,
			boolean 	editable
	) throws IllegalAccessException {
		Object[] header = {"#", "Nombre", "Pabellon", "Profesor"};

		return new DefaultTableModel(
				TableTypes.convertToTableData( objects ),
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
				return editable ?
						column == 1 || column == 2 || column == 3 :
						false;
			}
		};
	}

	private GeneralController<Clase> createGeneralController(
			List<Clase> clases
	) throws IllegalAccessException {
		DefaultTableModel tableModel = generalTableModel(clases, true);

		GeneralController<Clase> generalController = new GeneralController<Clase>(tableModel) {
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

		// TODO Dialog.
		//  Also, need to keep in mind that teh cellDialog structure is for changing that value, not only visualizing it.

		generalController.setCellDialog(
				"Pabellon",
				new ButtonTableRenderer() {
					@Override
					public String setButtonLabel(Object object) {
						Pabellon pabellon = (Pabellon) object;

						return pabellon.getName();
					}

					@Override
					public void onClickPanel(Object object) {
						List<Pabellon> pabellons = new ArrayList<>();
						pabellons.add( (Pabellon) object );

						PabellonController pabellonController = new PabellonController( pabellons );
						DefaultDialogController defaultDialogController = new DefaultDialogController( getWindow() );

						defaultDialogController.

						defaultDialogController.openDialog();
					}
				}
		);

		return generalController;
	}

	/**
	 * TODO: 'Alumno' Default Table model here
	 * @return
	 * @throws IllegalAccessException
	 */
	private GeneralController<Alumno> createSimpleController(
			List<Alumno> objects
	) throws IllegalAccessException {
		return new GeneralController<Alumno>(
				AlumnoController.generalTableModel( objects, false )
		) {
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
