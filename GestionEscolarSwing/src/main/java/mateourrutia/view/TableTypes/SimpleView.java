/*
 * Created by JFormDesigner on Tue May 28 15:42:24 ART 2024
 */

package mateourrutia.view.TableTypes;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author char2cs
 */
public class SimpleView extends JPanel {
    public SimpleView(
            DefaultTableModel model
    ) {
        initComponents();

        ScrollTable.setViewportView( new JTable(model) );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        SubCategoryTitle = new JLabel();
        ScrollTable = new JScrollPane();
        Table = new JTable();

        //======== this ========
        setLayout(new BorderLayout());

        //---- SubCategoryTitle ----
        SubCategoryTitle.setText("Testing");
        SubCategoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(SubCategoryTitle, BorderLayout.NORTH);

        //======== ScrollTable ========
        {
            ScrollTable.setViewportView(Table);
        }
        add(ScrollTable, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel SubCategoryTitle;
    private JScrollPane ScrollTable;
    private JTable Table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JLabel getSubCategoryTitle() {
        return SubCategoryTitle;
    }

    public JTable getTable() {
        return Table;
    }
}
