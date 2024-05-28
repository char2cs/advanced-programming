/*
 * Created by JFormDesigner on Tue May 28 12:24:05 ART 2024
 */

package mateourrutia.view.TableTypes;

import java.awt.*;
import javax.swing.*;

/**
 * @author char2cs
 */
public class ComplexView extends JPanel {
    public ComplexView(
            GeneralView generalView,
            SimpleView  simpleView
    ) {
        initComponents();
        GeneralView.add(generalView, BorderLayout.CENTER);
        SimpleView.add(simpleView, BorderLayout.SOUTH);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        GeneralView = new JPanel();
        SimpleView = new JPanel();

        //======== this ========
        setLayout(new BorderLayout());

        //======== GeneralView ========
        {
            GeneralView.setLayout(new BorderLayout());
        }
        add(GeneralView, BorderLayout.CENTER);

        //======== SimpleView ========
        {
            SimpleView.setLayout(new BorderLayout());
        }
        add(SimpleView, BorderLayout.SOUTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel GeneralView;
    private JPanel SimpleView;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JPanel getGeneralView() {
        return GeneralView;
    }

    public JPanel getSimpleView() {
        return SimpleView;
    }
}
