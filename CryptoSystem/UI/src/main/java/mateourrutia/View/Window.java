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

        //======== this ========
        setMinimumSize(new Dimension(768, 512));
        setPreferredSize(new Dimension(768, 512));
        setName("TableFrame");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }
}
