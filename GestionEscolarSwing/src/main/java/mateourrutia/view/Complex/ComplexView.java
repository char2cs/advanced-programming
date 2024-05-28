/*
 * Created by JFormDesigner on Tue May 28 12:24:05 ART 2024
 */

package mateourrutia.view.Complex;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class ComplexView extends JPanel {
    public ComplexView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, BorderLayout.NORTH);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());
            }
            panel1.add(panel2, BorderLayout.SOUTH);
        }
        add(panel1, BorderLayout.NORTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
