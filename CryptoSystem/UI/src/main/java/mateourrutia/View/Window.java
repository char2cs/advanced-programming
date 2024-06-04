/*
 * Created by JFormDesigner on Sat Jun 01 12:11:38 ART 2024
 */

package mateourrutia.View;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class Window extends JFrame {
    public Window() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        GlobalMenu = new JMenuBar();
        Window = new JMenu();
        TransactionHistory = new JMenuItem();

        //======== this ========
        setMinimumSize(new Dimension(768, 512));
        setPreferredSize(new Dimension(768, 512));
        setName("TableFrame");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== GlobalMenu ========
        {

            //======== Window ========
            {
                Window.setText("Ventana");

                //---- TransactionHistory ----
                TransactionHistory.setText("Ver transacciones en tiempo real");
                Window.add(TransactionHistory);
            }
            GlobalMenu.add(Window);
        }
        setJMenuBar(GlobalMenu);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar GlobalMenu;
    private JMenu Window;
    private JMenuItem TransactionHistory;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    public JMenuItem getTransactionHistory() {
        return TransactionHistory;
    }
}
