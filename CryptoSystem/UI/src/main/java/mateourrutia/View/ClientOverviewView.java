/*
 * Created by JFormDesigner on Sat Jun 01 14:47:44 ART 2024
 */

package mateourrutia.View;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class ClientOverviewView extends JPanel {
    public ClientOverviewView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Title = new JPanel();
        Welcome = new JLabel();
        clientName = new JLabel();
        hSpacer1 = new JPanel(null);
        hSpacer2 = new JPanel(null);
        label1 = new JLabel();
        Table = new JPanel();
        Buttons = new JPanel();
        Btn_Right = new JPanel();
        Back = new JButton();
        SelectedAccount = new JButton();
        Btn_Left = new JPanel();
        CreateType = new JComboBox();
        Create = new JButton();
        Delete = new JButton();

        //======== this ========
        setLayout(new BorderLayout());

        //======== Title ========
        {
            Title.setLayout(new BorderLayout());

            //---- Welcome ----
            Welcome.setText("Bienvenido,");
            Welcome.setFont(new Font("DejaVu Sans", Font.PLAIN, 28));
            Title.add(Welcome, BorderLayout.NORTH);

            //---- clientName ----
            clientName.setText("user_name");
            clientName.setFont(new Font("DejaVu Sans", Font.ITALIC, 48));
            clientName.setHorizontalAlignment(SwingConstants.LEFT);
            Title.add(clientName, BorderLayout.CENTER);
            Title.add(hSpacer1, BorderLayout.WEST);
            Title.add(hSpacer2, BorderLayout.EAST);

            //---- label1 ----
            label1.setText("Selecciona una Cuenta");
            label1.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            Title.add(label1, BorderLayout.SOUTH);
        }
        add(Title, BorderLayout.NORTH);

        //======== Table ========
        {
            Table.setLayout(new BorderLayout());
        }
        add(Table, BorderLayout.CENTER);

        //======== Buttons ========
        {
            Buttons.setLayout(new BorderLayout());

            //======== Btn_Right ========
            {
                Btn_Right.setLayout(new FlowLayout());

                //---- Back ----
                Back.setText("Volver");
                Btn_Right.add(Back);

                //---- SelectedAccount ----
                SelectedAccount.setText("Ir a Cuenta Seleccionada");
                Btn_Right.add(SelectedAccount);
            }
            Buttons.add(Btn_Right, BorderLayout.EAST);

            //======== Btn_Left ========
            {
                Btn_Left.setLayout(new FlowLayout(FlowLayout.LEFT));
                Btn_Left.add(CreateType);

                //---- Create ----
                Create.setText("Crear cuenta");
                Btn_Left.add(Create);

                //---- Delete ----
                Delete.setText("Borrar cuenta");
                Btn_Left.add(Delete);
            }
            Buttons.add(Btn_Left, BorderLayout.WEST);
        }
        add(Buttons, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel Title;
    private JLabel Welcome;
    private JLabel clientName;
    private JPanel hSpacer1;
    private JPanel hSpacer2;
    private JLabel label1;
    private JPanel Table;
    private JPanel Buttons;
    private JPanel Btn_Right;
    private JButton Back;
    private JButton SelectedAccount;
    private JPanel Btn_Left;
    private JComboBox CreateType;
    private JButton Create;
    private JButton Delete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JLabel getClientName() {
        return clientName;
    }

    public JPanel getTable() {
        return Table;
    }

    public JButton getBack() {
        return Back;
    }

    public JButton getSelectedAccount() {
        return SelectedAccount;
    }

    public JComboBox getCreateType() {
        return CreateType;
    }

    public JButton getCreate() {
        return Create;
    }

    public JButton getDelete() {
        return Delete;
    }
}
