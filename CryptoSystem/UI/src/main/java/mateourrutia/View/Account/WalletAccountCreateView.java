/*
 * Created by JFormDesigner on Sun Jun 02 21:21:55 ART 2024
 */

package mateourrutia.View.Account;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class WalletAccountCreateView extends JPanel {
    public WalletAccountCreateView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        panel2 = new JPanel();
        Dropdown = new JComboBox();
        label3 = new JLabel();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //---- label1 ----
            label1.setText("Crear una Wallet");
            label1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
            panel1.add(label1, BorderLayout.CENTER);

            //---- label2 ----
            label2.setText("Selecciona un tipo de criptomoneda");
            panel1.add(label2, BorderLayout.SOUTH);
        }
        add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new FlowLayout());
            panel2.add(Dropdown);

            //---- label3 ----
            label3.setText("*Se permite solo una wallet por criptomoneda");
            panel2.add(label3);
        }
        add(panel2, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel2;
    private JComboBox Dropdown;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JComboBox getDropdown() {
        return Dropdown;
    }
}
