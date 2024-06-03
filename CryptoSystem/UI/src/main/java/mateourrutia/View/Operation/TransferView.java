/*
 * Created by JFormDesigner on Mon Jun 03 12:07:58 ART 2024
 */

package mateourrutia.View.Operation;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class TransferView extends JPanel {
    public TransferView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Title = new JLabel();
        Body = new JPanel();
        CBUPanel = new JPanel();
        CBUText = new JLabel();
        CBU = new JTextField();
        BalancePanel = new JPanel();
        label2 = new JLabel();
        Balance = new JTextField();

        //======== this ========
        setLayout(new BorderLayout());

        //---- Title ----
        Title.setText("Transferir fondos");
        Title.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        add(Title, BorderLayout.NORTH);

        //======== Body ========
        {
            Body.setLayout(new GridLayout(0, 1));

            //======== CBUPanel ========
            {
                CBUPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- CBUText ----
                CBUText.setText("CBU destino");
                CBUPanel.add(CBUText);

                //---- CBU ----
                CBU.setColumns(12);
                CBUPanel.add(CBU);
            }
            Body.add(CBUPanel);

            //======== BalancePanel ========
            {
                BalancePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                //---- label2 ----
                label2.setText("Fondos a transferir");
                BalancePanel.add(label2);

                //---- Balance ----
                Balance.setColumns(12);
                BalancePanel.add(Balance);
            }
            Body.add(BalancePanel);
        }
        add(Body, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel Title;
    private JPanel Body;
    private JPanel CBUPanel;
    private JLabel CBUText;
    private JTextField CBU;
    private JPanel BalancePanel;
    private JLabel label2;
    private JTextField Balance;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JTextField getCBU() {
        return CBU;
    }

    public JTextField getBalance() {
        return Balance;
    }
}
