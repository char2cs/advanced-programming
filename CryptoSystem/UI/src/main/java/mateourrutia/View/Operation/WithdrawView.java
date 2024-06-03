/*
 * Created by JFormDesigner on Mon Jun 03 11:45:57 ART 2024
 */

package mateourrutia.View.Operation;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class WithdrawView extends JPanel {
    public WithdrawView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        Balance = new JTextField();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("Retirar");
        label1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label2 ----
            label2.setText("Seleccione un monto para retirar");
            panel1.add(label2);

            //---- Balance ----
            Balance.setColumns(12);
            panel1.add(Balance);
        }
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField Balance;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JTextField getBalance() {
        return Balance;
    }
}
