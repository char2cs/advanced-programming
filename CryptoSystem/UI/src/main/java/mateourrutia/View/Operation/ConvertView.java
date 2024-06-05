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
        panel4 = new JPanel();
        label3 = new JLabel();
        CBU = new JTextField();
        panel3 = new JPanel();
        label1 = new JLabel();
        Balance = new JTextField();
        scrollPane1 = new JScrollPane();
        label4 = new JLabel();

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

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label3 ----
                label3.setText("Cuenta a transferir");
                panel4.add(label3);

                //---- CBU ----
                CBU.setColumns(12);
                panel4.add(CBU);
            }
            panel1.add(panel4);

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

            //======== scrollPane1 ========
            {

                //---- label4 ----
                label4.setText("La conversion se realizara dependiendo del tipo de moneda destino");
                scrollPane1.setViewportView(label4);
            }
            panel1.add(scrollPane1);
        }
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel4;
    private JLabel label3;
    private JTextField CBU;
    private JPanel panel3;
    private JLabel label1;
    private JTextField Balance;
    private JScrollPane scrollPane1;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JTextField getBalance() {
        return Balance;
    }

    public JTextField getCBU() {
        return CBU;
    }
}
