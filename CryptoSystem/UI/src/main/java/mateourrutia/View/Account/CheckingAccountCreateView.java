/*
 * Created by JFormDesigner on Sun Jun 02 15:58:21 ART 2024
 */

package mateourrutia.View.Account;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class CheckingAccountCreateView extends JPanel {
    public CheckingAccountCreateView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel2 = new JPanel();
        panel1 = new JPanel();
        label2 = new JLabel();
        Overdraft = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        Currency = new JComboBox();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("Estas apunto de crear tu cuenta corriente");
        label1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(0, 1));

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
                ((FlowLayout)panel1.getLayout()).setAlignOnBaseline(true);

                //---- label2 ----
                label2.setText("Limite para retirar");
                panel1.add(label2);

                //---- Overdraft ----
                Overdraft.setColumns(10);
                panel1.add(Overdraft);
            }
            panel2.add(panel1);

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label3 ----
                label3.setText("Tipo de moneda");
                panel3.add(label3);
                panel3.add(Currency);
            }
            panel2.add(panel3);
        }
        add(panel2, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JPanel panel2;
    private JPanel panel1;
    private JLabel label2;
    private JTextField Overdraft;
    private JPanel panel3;
    private JLabel label3;
    private JComboBox Currency;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public String getOverdraft() {
        return Overdraft.getText();
    }

    public JComboBox getCurrency() {
        return Currency;
    }
}
