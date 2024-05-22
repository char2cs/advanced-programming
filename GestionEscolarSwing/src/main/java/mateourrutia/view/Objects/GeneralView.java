/*
 * Created by JFormDesigner on Wed May 22 15:42:33 ART 2024
 */

package mateourrutia.view.Objects;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class GeneralView extends JPanel {
    public GeneralView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tableScroll = new JScrollPane();
        Table = new JTable();
        Buttons = new JPanel();
        BtnRight = new JPanel();
        Add = new JButton();
        BtnLeft = new JPanel();
        Update = new JButton();
        Delete = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== tableScroll ========
        {
            tableScroll.setViewportView(Table);
        }
        add(tableScroll, BorderLayout.CENTER);

        //======== Buttons ========
        {
            Buttons.setLayout(new BorderLayout());

            //======== BtnRight ========
            {
                BtnRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- Add ----
                Add.setText("Agregar");
                BtnRight.add(Add);
            }
            Buttons.add(BtnRight, BorderLayout.EAST);

            //======== BtnLeft ========
            {
                BtnLeft.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- Update ----
                Update.setText("Actualizar seleccionados");
                BtnLeft.add(Update);

                //---- Delete ----
                Delete.setText("Borrar seleccionados");
                BtnLeft.add(Delete);
            }
            Buttons.add(BtnLeft, BorderLayout.WEST);
        }
        add(Buttons, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane tableScroll;
    private JTable Table;
    private JPanel Buttons;
    private JPanel BtnRight;
    private JButton Add;
    private JPanel BtnLeft;
    private JButton Update;
    private JButton Delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JButton getUpdate() {
        return Update;
    }

    public JButton getDelete() {
        return Delete;
    }

    public JButton getAdd() {
        return Add;
    }

    public JTable getTable() {
        return Table;
    }
}
