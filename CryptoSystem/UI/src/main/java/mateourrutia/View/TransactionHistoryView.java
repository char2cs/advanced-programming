/*
 * Created by JFormDesigner on Mon Jun 03 22:20:41 ART 2024
 */

package mateourrutia.View;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class TransactionHistoryView extends JPanel {
    public TransactionHistoryView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label3 = new JLabel();
        Content = new JPanel();
        Buttons = new JPanel();
        Filters = new JLabel();
        FilterButtonPanel = new JPanel();
        Filter = new JButton();
        Reset = new JButton();
        FilterContent = new JPanel();
        ButtonCBU = new JPanel();
        label5 = new JLabel();
        CBUText = new JTextField();
        MontoMin = new JPanel();
        label4 = new JLabel();
        BalanceMin = new JTextField();
        MontoMax = new JPanel();
        label6 = new JLabel();
        BalanceMax = new JTextField();
        ButtonsBottom = new JPanel();
        label2 = new JLabel();
        State = new JComboBox();
        label1 = new JLabel();
        Type = new JComboBox();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label3 ----
        label3.setText("Historial de Transacciones");
        label3.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        add(label3, BorderLayout.NORTH);

        //======== Content ========
        {
            Content.setLayout(new BorderLayout());
        }
        add(Content, BorderLayout.CENTER);

        //======== Buttons ========
        {
            Buttons.setLayout(new BorderLayout());

            //---- Filters ----
            Filters.setText("Filtros");
            Filters.setHorizontalAlignment(SwingConstants.CENTER);
            Filters.setFont(new Font("DejaVu Sans", Font.ITALIC, 16));
            Buttons.add(Filters, BorderLayout.PAGE_START);

            //======== FilterButtonPanel ========
            {
                FilterButtonPanel.setLayout(new GridLayout());

                //---- Filter ----
                Filter.setText("Filtrar");
                FilterButtonPanel.add(Filter);

                //---- Reset ----
                Reset.setText("Reset");
                FilterButtonPanel.add(Reset);
            }
            Buttons.add(FilterButtonPanel, BorderLayout.PAGE_END);

            //======== FilterContent ========
            {
                FilterContent.setLayout(new GridLayout(12, 1));

                //======== ButtonCBU ========
                {
                    ButtonCBU.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- label5 ----
                    label5.setText("CBU");
                    ButtonCBU.add(label5);

                    //---- CBUText ----
                    CBUText.setColumns(12);
                    ButtonCBU.add(CBUText);
                }
                FilterContent.add(ButtonCBU);

                //======== MontoMin ========
                {
                    MontoMin.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- label4 ----
                    label4.setText("Monto Min.");
                    MontoMin.add(label4);

                    //---- BalanceMin ----
                    BalanceMin.setBackground(new Color(0xd6d9df));
                    BalanceMin.setColumns(12);
                    MontoMin.add(BalanceMin);
                }
                FilterContent.add(MontoMin);

                //======== MontoMax ========
                {
                    MontoMax.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //---- label6 ----
                    label6.setText("Monto Max.");
                    MontoMax.add(label6);

                    //---- BalanceMax ----
                    BalanceMax.setBackground(new Color(0xd6d9df));
                    BalanceMax.setColumns(11);
                    MontoMax.add(BalanceMax);
                }
                FilterContent.add(MontoMax);
            }
            Buttons.add(FilterContent, BorderLayout.CENTER);
        }
        add(Buttons, BorderLayout.WEST);

        //======== ButtonsBottom ========
        {
            ButtonsBottom.setLayout(new FlowLayout());

            //---- label2 ----
            label2.setText("Estado");
            ButtonsBottom.add(label2);
            ButtonsBottom.add(State);

            //---- label1 ----
            label1.setText("Tipo de transaccion");
            ButtonsBottom.add(label1);
            ButtonsBottom.add(Type);
        }
        add(ButtonsBottom, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label3;
    private JPanel Content;
    private JPanel Buttons;
    private JLabel Filters;
    private JPanel FilterButtonPanel;
    private JButton Filter;
    private JButton Reset;
    private JPanel FilterContent;
    private JPanel ButtonCBU;
    private JLabel label5;
    private JTextField CBUText;
    private JPanel MontoMin;
    private JLabel label4;
    private JTextField BalanceMin;
    private JPanel MontoMax;
    private JLabel label6;
    private JTextField BalanceMax;
    private JPanel ButtonsBottom;
    private JLabel label2;
    private JComboBox State;
    private JLabel label1;
    private JComboBox Type;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JPanel getContent() {
        return Content;
    }

    public void setContent(
            JPanel component
    ) {
        Content.removeAll();
        Content.add(component, BorderLayout.CENTER);
        Content.revalidate();
        Content.repaint();
    }

    public JTextField getBalanceMax() {
        return BalanceMax;
    }

    public JTextField getBalanceMin() {
        return BalanceMin;
    }

    public JTextField getCBUText() {
        return CBUText;
    }

    public JButton getFilter() {
        return Filter;
    }

    public JButton getReset() {
        return Reset;
    }

    public JComboBox getType() {
        return Type;
    }

    public JComboBox getState() {
        return State;
    }
}
