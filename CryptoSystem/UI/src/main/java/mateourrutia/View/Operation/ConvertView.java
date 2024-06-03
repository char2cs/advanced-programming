/*
 * Created by JFormDesigner on Mon Jun 03 14:53:18 ART 2024
 */

package mateourrutia.View.Operation;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class ConvertView extends JPanel {
    public ConvertView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        panel1 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        Balance = new JTextField();
        panel2 = new JPanel();
        label3 = new JLabel();
        Crypto = new JComboBox();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label2 ----
        label2.setText("Convertir");
        label2.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        add(label2, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(0, 1));

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label1 ----
                label1.setText("Cant. de fondos a convertir");
                panel3.add(label1);

                //---- Balance ----
                Balance.setColumns(12);
                panel3.add(Balance);
            }
            panel1.add(panel3);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label3 ----
                label3.setText("Convertir a");
                panel2.add(label3);
                panel2.add(Crypto);
            }
            panel1.add(panel2);
        }
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel3;
    private JLabel label1;
    private JTextField Balance;
    private JPanel panel2;
    private JLabel label3;
    private JComboBox Crypto;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JTextField getBalance() {
        return Balance;
    }

    public JComboBox getCrypto() {
        return Crypto;
    }
}
