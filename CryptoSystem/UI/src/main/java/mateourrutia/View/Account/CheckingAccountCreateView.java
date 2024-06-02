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
        panel1 = new JPanel();
        label2 = new JLabel();
        Overdraft = new JTextField();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("Estas apunto de crear tu cuenta corriente");
        label1.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, BorderLayout.NORTH);

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
        add(panel1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField Overdraft;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public String getOverdraft() {
        return Overdraft.getText();
    }
}
