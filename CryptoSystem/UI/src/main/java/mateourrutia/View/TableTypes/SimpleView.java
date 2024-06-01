package mateourrutia.View.TableTypes;

/*
 * Created by JFormDesigner on Wed May 22 15:42:33 ART 2024
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author char2cs
 */
public class SimpleView extends JPanel {
	public SimpleView(DefaultTableModel tableModel ) {
		initComponents( tableModel );
	}

	private void initComponents( DefaultTableModel tableModel ) {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		tableScroll = new JScrollPane();
		Table = new JTable( tableModel );

		//======== this ========
		setLayout(new BorderLayout());

		//======== tableScroll ========
		{
			tableScroll.setViewportView(Table);
		}
		add(tableScroll, BorderLayout.CENTER);

		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	private JScrollPane tableScroll;
	private JTable Table;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

	public JTable getTable() {
		return Table;
	}

	public void setTable(JTable table) {
		Table = table;
	}

}