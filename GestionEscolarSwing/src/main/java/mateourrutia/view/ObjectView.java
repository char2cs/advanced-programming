/*
 * Created by JFormDesigner on Wed May 22 12:12:57 ART 2024
 */

package mateourrutia.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;

/**
 * @author char2cs
 */
public class ObjectView extends JFrame {
    public ObjectView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        FIle = new JMenu();
        NewEntity = new JMenuItem();
        Exit = new JMenuItem();
        View = new JMenu();
        consoleBtn = new JMenuItem();
        dialogPane = new JPanel();
        EntitiesScroll = new JScrollPane();
        Entities = new JTree();
        Scene = new JPanel();

        //======== this ========
        setTitle("Gestion Escolar");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== FIle ========
            {
                FIle.setText("File");

                //---- NewEntity ----
                NewEntity.setText("New Entity");
                FIle.add(NewEntity);

                //---- Exit ----
                Exit.setText("Exit");
                FIle.add(Exit);
            }
            menuBar1.add(FIle);

            //======== View ========
            {
                View.setText("View");

                //---- consoleBtn ----
                consoleBtn.setText("Open console logs");
                View.add(consoleBtn);
            }
            menuBar1.add(View);
        }
        setJMenuBar(menuBar1);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== EntitiesScroll ========
            {
                EntitiesScroll.setMinimumSize(new Dimension(500, 22));

                //---- Entities ----
                Entities.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("(root)") {
                        {
                            add(new DefaultMutableTreeNode("Profesor"));
                            add(new DefaultMutableTreeNode("Alumno"));
                            add(new DefaultMutableTreeNode("Mantenimiento"));
                            add(new DefaultMutableTreeNode("Administrativo"));
                            add(new DefaultMutableTreeNode("Curso"));
                            add(new DefaultMutableTreeNode("Carrera"));
                            add(new DefaultMutableTreeNode("Pabellon"));
                        }
                    }));
                Entities.setBorder(null);
                Entities.setRootVisible(false);
                Entities.setShowsRootHandles(false);
                Entities.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                EntitiesScroll.setViewportView(Entities);
            }
            dialogPane.add(EntitiesScroll, BorderLayout.WEST);

            //======== Scene ========
            {
                Scene.setLayout(new BorderLayout());
            }
            dialogPane.add(Scene, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar1;
    private JMenu FIle;
    private JMenuItem NewEntity;
    private JMenuItem Exit;
    private JMenu View;
    private JMenuItem consoleBtn;
    private JPanel dialogPane;
    private JScrollPane EntitiesScroll;
    private JTree Entities;
    private JPanel Scene;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public JMenuItem getExit() {
        return Exit;
    }

    public JTree getEntities() {
        return Entities;
    }

    public JPanel getScene() {
        return Scene;
    }

    public void setScene(JPanel sceneContent) {
        Scene.removeAll();
        Scene.add(sceneContent, BorderLayout.CENTER);
        Scene.revalidate();
        Scene.repaint();
    }

}
