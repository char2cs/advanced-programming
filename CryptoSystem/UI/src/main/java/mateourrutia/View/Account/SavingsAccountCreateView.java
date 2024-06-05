/*
 * Created by JFormDesigner on Wed Jun 05 00:23:23 ART 2024
 */

package mateourrutia.View.Account;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class SavingsAccountCreateView extends JPanel {
    public SavingsAccountCreateView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel3 = new JPanel();
        label3 = new JLabel();
        Currency = new JComboBox();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("Estas apunto de crear tu cuenta");
        label1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, BorderLayout.NORTH);

        //======== panel3 ========
        {
            panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- label3 ----
            label3.setText("Tipo de moneda");
            panel3.add(label3);
            panel3.add(Currency);
        }
        add(panel3, BorderLayout.WEST);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JPanel panel3;
    private JLabel label3;
    private JComboBox Currency;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JComboBox getCurrency() {
        return Currency;
    }
}
