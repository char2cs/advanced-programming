/*
 * Created by JFormDesigner on Tue Jun 04 18:19:06 ART 2024
 */

package mateourrutia.View.Tables;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class ClientCRUDView extends JPanel {
    public ClientCRUDView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        GoBack = new JButton();
        TestingCases = new JButton();

        //======== this ========
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        //---- GoBack ----
        GoBack.setText("Atras");
        add(GoBack);

        //---- TestingCases ----
        TestingCases.setText("Agregar casos de prueba");
        add(TestingCases);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton GoBack;
    private JButton TestingCases;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public JButton getGoBack() {
        return GoBack;
    }

    public JButton getTestingCases() {
        return TestingCases;
    }
}
