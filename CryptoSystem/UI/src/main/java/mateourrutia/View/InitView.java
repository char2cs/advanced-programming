/*
 * Created by JFormDesigner on Sat Jun 01 09:44:06 ART 2024
 */

package mateourrutia.View;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author char2cs
 */
public class InitView extends JFrame {
    public InitView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        TitleContainer = new JPanel();
        Title = new JLabel();
        label1 = new JLabel();
        TablePanel = new JPanel();
        BUttons = new JPanel();
        ButtonRight = new JPanel();
        Exit = new JButton();
        UserSelect = new JButton();
        ButtonLeft = new JPanel();
        TransactionHistory = new JButton();
        AllClients = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(768, 768));
        setPreferredSize(new Dimension(768, 768));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== TitleContainer ========
        {
            TitleContainer.setLayout(new BorderLayout());

            //---- Title ----
            Title.setText("Crypto System");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Title.setFont(new Font("DejaVu Sans", Font.PLAIN, 48));
            TitleContainer.add(Title, BorderLayout.CENTER);

            //---- label1 ----
            label1.setText("Por Urrutia Mateo");
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setFont(new Font("DejaVu Sans", Font.ITALIC, 20));
            TitleContainer.add(label1, BorderLayout.SOUTH);
        }
        contentPane.add(TitleContainer, BorderLayout.NORTH);

        //======== TablePanel ========
        {
            TablePanel.setMinimumSize(new Dimension(1024, 768));
            TablePanel.setLayout(new BorderLayout());
        }
        contentPane.add(TablePanel, BorderLayout.CENTER);

        //======== BUttons ========
        {
            BUttons.setLayout(new BorderLayout());

            //======== ButtonRight ========
            {
                ButtonRight.setLayout(new FlowLayout());

                //---- Exit ----
                Exit.setText("Salir");
                ButtonRight.add(Exit);

                //---- UserSelect ----
                UserSelect.setText("Seleccionar Usuario");
                ButtonRight.add(UserSelect);
            }
            BUttons.add(ButtonRight, BorderLayout.EAST);

            //======== ButtonLeft ========
            {
                ButtonLeft.setLayout(new FlowLayout());

                //---- TransactionHistory ----
                TransactionHistory.setText("Ver Historial de Transacciones");
                ButtonLeft.add(TransactionHistory);

                //---- AllClients ----
                AllClients.setText("ABM de Cuentas");
                ButtonLeft.add(AllClients);
            }
            BUttons.add(ButtonLeft, BorderLayout.WEST);
        }
        contentPane.add(BUttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel TitleContainer;
    private JLabel Title;
    private JLabel label1;
    private JPanel TablePanel;
    private JPanel BUttons;
    private JPanel ButtonRight;
    private JButton Exit;
    private JButton UserSelect;
    private JPanel ButtonLeft;
    private JButton TransactionHistory;
    private JButton AllClients;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JPanel getTablePanel() {
        return TablePanel;
    }

    public JButton getExit() {
        return Exit;
    }

    public JButton getUserSelect() {
        return UserSelect;
    }

    public JButton getTransactionHistory() {
        return TransactionHistory;
    }

    public JButton getAllClients() {
        return AllClients;
    }
}
