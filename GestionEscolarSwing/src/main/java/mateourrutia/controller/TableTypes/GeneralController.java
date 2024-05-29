package mateourrutia.controller.TableTypes;

import mateourrutia.render.ButtonTable.ButtonEditor;
import mateourrutia.render.ButtonTable.ButtonRenderer;
import mateourrutia.render.ButtonTable.ButtonTableRenderer;
import mateourrutia.view.TableTypes.GeneralView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Arrays;
import java.util.Collections;

public abstract class GeneralController<T> extends TableTypes<T> {
    private GeneralView view;

    public GeneralController() {}

    public GeneralController(
            DefaultTableModel model
    ) {
        setModel( model );
    }

    protected void setModel(DefaultTableModel model) {
        this.model  = model;
        this.view   = new GeneralView( this.model );

        setTableModelListeners();
        setUpListeners();
    }

    public GeneralView getView() {
        return view;
    }

    /**
     * Will create a button inside each row's cell specified.
     *
     * @param identifier
     * @param buttonTableRenderer
     */
    public void setCellDialog(
            String              identifier,
            ButtonTableRenderer buttonTableRenderer
    ) {
        this.view.getTable()
                .getColumn(identifier).setCellRenderer( buttonTableRenderer.getButtonRenderer() );
        this.view.getTable()
                .getColumn(identifier).setCellEditor( buttonTableRenderer.getButtonEditor() );
    }

    // Internal methods
    private void setUpListeners() {
        JTable table = view.getTable();

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

        view.getDelete().addActionListener(new ActionListener() {
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

        view.getAdd().addActionListener(new ActionListener() {
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
