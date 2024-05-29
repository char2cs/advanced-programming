package mateourrutia.controller.Objects;

import java.util.ArrayList;
import java.util.List;

import mateourrutia.DAO.imp.AlumnoDAOImp;
import mateourrutia.controller.TableTypes.ComplexController;
import mateourrutia.controller.TableTypes.GeneralController;
import mateourrutia.controller.TableTypes.TableTypes;
import mateourrutia.domain.Alumno;
import mateourrutia.domain.Clase;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class AlumnoController extends ComplexController<Alumno, Clase> {
    AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

    public AlumnoController() {
        try {
            this.setControllers(
                    createGeneralController( alumnoDAOImp.getAll() ),
					createSimpleController( new ArrayList<>() )
            );
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

	public static DefaultTableModel generalTableModel(
			List<Alumno> 	alumnos,
			boolean 		editable
	) throws IllegalAccessException {
		Object[] header = {"#", "DNI", "Nombre", "Apellido", "Carrera"};

		return new DefaultTableModel(
				TableTypes.convertToTableData( alumnos ),
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
						column == 2 || column == 3 || column == 4 :
						false;
			}
		};
	}

    private GeneralController<Alumno> createGeneralController(
            List<Alumno> alumnos
    ) throws IllegalAccessException {
		DefaultTableModel tableModel = generalTableModel(alumnos, true);

        return new GeneralController<Alumno>(tableModel) {
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

    private GeneralController<Clase> createSimpleController(
            List<Clase> clases
    ) throws IllegalAccessException {
		return new GeneralController<Clase>(ClaseController.generalTableModel(clases, false)) {
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

}
