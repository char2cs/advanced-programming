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
        ButtonRight = new JPanel();
        label2 = new JLabel();
        comboBox1 = new JComboBox();
        label1 = new JLabel();
        Type = new JComboBox();
        ButtonLeft = new JPanel();
        textField1 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //---- label3 ----
        label3.setText("Historial de Transacciones");
        label3.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        add(label3, BorderLayout.NORTH);

        //======== Content ========
        {
            Content.setLayout(new FlowLayout());
        }
        add(Content, BorderLayout.CENTER);

        //======== Buttons ========
        {
            Buttons.setLayout(new BorderLayout());

            //======== ButtonRight ========
            {
                ButtonRight.setLayout(new FlowLayout());

                //---- label2 ----
                label2.setText("Estado");
                ButtonRight.add(label2);
                ButtonRight.add(comboBox1);

                //---- label1 ----
                label1.setText("Tipo");
                ButtonRight.add(label1);
                ButtonRight.add(Type);
            }
            Buttons.add(ButtonRight, BorderLayout.EAST);

            //======== ButtonLeft ========
            {
                ButtonLeft.setLayout(new FlowLayout());

                //---- textField1 ----
                textField1.setColumns(12);
                ButtonLeft.add(textField1);

                //---- button1 ----
                button1.setText("Filtrar CBU");
                ButtonLeft.add(button1);
            }
            Buttons.add(ButtonLeft, BorderLayout.WEST);
        }
        add(Buttons, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label3;
    private JPanel Content;
    private JPanel Buttons;
    private JPanel ButtonRight;
    private JLabel label2;
    private JComboBox comboBox1;
    private JLabel label1;
    private JComboBox Type;
    private JPanel ButtonLeft;
    private JTextField textField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
