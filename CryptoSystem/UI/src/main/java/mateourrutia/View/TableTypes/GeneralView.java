/*
 * Created by JFormDesigner on Wed May 22 15:42:33 ART 2024
 */

package mateourrutia.View.TableTypes;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author char2cs
 */
public class GeneralView extends JPanel {
	public GeneralView(DefaultTableModel tableModel ) {
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

		//======== Buttons ========
		{
			Buttons.setLayout(new BorderLayout());

			//======== BtnRight ========
			{
				BtnRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

				//---- Delete ----
				Delete.setText("Borrar seleccionados");
				BtnRight.add(Delete);

				//---- Add ----
				Add.setText("Agregar +");
				BtnRight.add(Add);
			}
			Buttons.add(BtnRight, BorderLayout.EAST);
		}
		add(Buttons, BorderLayout.SOUTH);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	private JScrollPane tableScroll;
	private JTable Table;
	private JPanel Buttons;
	private JPanel BtnRight;
	private JButton Delete;
	private JButton Add;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

	public JButton getDelete() {
		return Delete;
	}

	public JButton getAdd() {
		return Add;
	}

	public JTable getTable() {
		return Table;
	}

	public void setTable(JTable table) {
		Table = table;
	}

}