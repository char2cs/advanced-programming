//package mateourrutia.controller.Objects;
//
//import java.util.List;
//
//import mateourrutia.DAO.imp.AlumnoDAOImp;
//import mateourrutia.controller.TableTypes.ComplexController;
//import mateourrutia.controller.TableTypes.GeneralController;
//import mateourrutia.controller.TableTypes.SimpleController;
//import mateourrutia.controller.TableTypes.TableTypes;
//import mateourrutia.domain.Alumno;
//import mateourrutia.domain.Clase;
//
//import javax.swing.*;
//import javax.swing.event.TableModelEvent;
//import javax.swing.table.DefaultTableModel;
//
//public class AlumnoController extends ComplexController<Alumno, Clase> {
//    AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();
//
//    public AlumnoController() {
//        try {
//            this.setControllers(
//                    createGeneralController( alumnoDAOImp.getAll() ),
//                    createSimpleController()
//            );
//        }
//        catch (IllegalAccessException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    public GeneralController<Alumno> createGeneralController(
//            List<Alumno> alumnos
//    ) throws IllegalAccessException {
//        Object[] header = {"#", "DNI", "Nombre", "Apellido", "Carrera"};
//
//        DefaultTableModel tableModel = new DefaultTableModel(
//                TableTypes.convertToTableData( alumnos ),
//                header
//        ) {
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                if (columnIndex == 0)
//                    return Integer.class;
//
//                return super.getColumnClass(columnIndex);
//            }
//
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return column == 2 || column == 3 || column == 4;
//            }
//        };
//
//        return new GeneralController<Alumno>(tableModel) {
//            @Override
//            protected Alumno onAdd() throws ClassCastException {
//                return null;
//            }
//
//            @Override
//            protected void onUpdate(TableModelEvent e) throws ClassCastException {
//
//            }
//
//            @Override
//            protected void onDelete(Integer[] rows, JTable table) throws ClassCastException {
//
//            }
//        };
//    }
//
//    private SimpleController<Clase> createSimpleController(
//            List<Clase> clases
//    ) throws IllegalAccessException {
//        ClaseController claseController = new ClaseController();
//
//        return claseController.createGeneralController();
//    }
//
//}
