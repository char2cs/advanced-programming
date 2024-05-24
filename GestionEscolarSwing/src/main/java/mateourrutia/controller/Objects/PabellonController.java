package mateourrutia.controller.Objects;

import mateourrutia.DAO.imp.PabellonDAOImp;
import mateourrutia.controller.GeneralController;
import mateourrutia.domain.Pabellon;

import javax.swing.table.DefaultTableModel;

public class PabellonController extends GeneralController<Pabellon> {

    private final PabellonDAOImp pabellonDAOImp = new PabellonDAOImp();

    public PabellonController() {
        super();

        try {
            Object[] header = {"#", "Nombre", "Ubicacion"};

            DefaultTableModel tableModel = new DefaultTableModel(
                    convertToTableData( pabellonDAOImp.getAll() ),
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
                    return column == 1 || column == 2;
                }
            };

            this.setModel( tableModel );
        }
        catch ( IllegalAccessException e ) {
            e.printStackTrace();
        }

        return;
    }

}
