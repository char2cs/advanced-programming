package mateourrutia.controller.Objects;

import mateourrutia.DAO.imp.PabellonDAOImp;
import mateourrutia.controller.TableTypes.GeneralController;
import mateourrutia.domain.Pabellon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import java.util.List;

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
            throw new RuntimeException(e);
        }
    }

    // Table Model Listeners
    @Override
    protected Pabellon onAdd() throws ClassCastException {
        List<Pabellon> pabellons = pabellonDAOImp.getAll();
        Pabellon pabellon;

        if ( !pabellons.isEmpty() )
            pabellon = new Pabellon(
                    pabellons.get(pabellons.size() - 1).getId() + 1,
                    "",
                    ""
            );
        else
            pabellon = new Pabellon(
                    0,
                    "",
                    ""
            );

        pabellonDAOImp.add( pabellon );

        return pabellon;
    }

    @Override
    protected void onUpdate(
            TableModelEvent e
    ) throws ClassCastException {
        DefaultTableModel Model = this.getModel();
        int row                 = e.getFirstRow();
        int column              = e.getColumn();

        Object      newValue   = Model.getValueAt(row, column);
        Pabellon    pabellon   = pabellonDAOImp.get(
                (int) Model.getValueAt(row, 0)
        );

        switch (column) {
            case 0:
                break;
            case 1:
                pabellon.setName( (String) newValue );
                break;
            case 2:
                pabellon.setUbicacion( (String) newValue );
                break;
        }

        pabellonDAOImp.update( pabellon );
    }

    @Override
    protected void onDelete(
            Integer[] rows,
            JTable table
    ) throws ClassCastException {
        for ( int row : rows )
        {
            pabellonDAOImp.delete( (int) table.getValueAt(row, 0) );
            this.removeRow( row );
        }
    }
}
