/*
 * Created by JFormDesigner on Sun Jun 02 22:13:13 ART 2024
 */

package mateourrutia.View;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class AccountOverviewView extends JPanel {
    public AccountOverviewView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Account = new JLabel();
        hSpacer1 = new JPanel(null);
        Buttons = new JPanel();
        Balance = new JLabel();
        vSpacer1 = new JPanel(null);
        Deposit = new JButton();
        Transaction = new JButton();
        Withdraw = new JButton();
        Convert = new JButton();
        hSpacer2 = new JPanel(null);
        panel2 = new JPanel();
        Back = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //---- Account ----
        Account.setText("Cuenta ...");
        Account.setFont(new Font("DejaVu Sans", Font.ITALIC, 20));
        Account.setHorizontalAlignment(SwingConstants.CENTER);
        add(Account, BorderLayout.NORTH);
        add(hSpacer1, BorderLayout.WEST);

        //======== Buttons ========
        {
            Buttons.setLayout(new GridLayout(0, 1));

            //---- Balance ----
            Balance.setText("$XXXXXXXX");
            Balance.setFont(new Font("Droid Sans", Font.BOLD, 60));
            Balance.setHorizontalAlignment(SwingConstants.CENTER);
            Buttons.add(Balance);
            Buttons.add(vSpacer1);

            //---- Deposit ----
            Deposit.setText("Deposito");
            Deposit.setFont(new Font("DejaVu Sans", Font.PLAIN, 26));
            Buttons.add(Deposit);

            //---- Transaction ----
            Transaction.setText("Transacci\u00f3n");
            Transaction.setFont(new Font("DejaVu Sans", Font.PLAIN, 26));
            Buttons.add(Transaction);

            //---- Withdraw ----
            Withdraw.setText("Retirar");
            Withdraw.setFont(new Font("DejaVu Sans", Font.PLAIN, 26));
            Buttons.add(Withdraw);

            //---- Convert ----
            Convert.setText("Conversion");
            Convert.setFont(new Font("DejaVu Sans", Font.PLAIN, 26));
            Buttons.add(Convert);
        }
        add(Buttons, BorderLayout.CENTER);
        add(hSpacer2, BorderLayout.EAST);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //---- Back ----
            Back.setText("Volver");
            panel2.add(Back, BorderLayout.EAST);
        }
        add(panel2, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel Account;
    private JPanel hSpacer1;
    private JPanel Buttons;
    private JLabel Balance;
    private JPanel vSpacer1;
    private JButton Deposit;
    private JButton Transaction;
    private JButton Withdraw;
    private JButton Convert;
    private JPanel hSpacer2;
    private JPanel panel2;
    private JButton Back;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JLabel getBalance() {
        return Balance;
    }

    public JLabel getAccount() {
        return Account;
    }

    public JButton getWithdraw() {
        return Withdraw;
    }

    public JButton getTransaction() {
        return Transaction;
    }

    public JButton getDeposit() {
        return Deposit;
    }

    public JButton getConvert() {
        return Convert;
    }

    public JButton getBack() {
        return Back;
    }
}
