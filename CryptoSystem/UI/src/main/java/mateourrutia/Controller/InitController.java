package mateourrutia.Controller;

import mateourrutia.Controller.Domain.ClientSimple;
import mateourrutia.View.InitView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitController {
	private InitView view;
	private ClientSimple clientSimple = new ClientSimple();

	public InitController() {
		view = new InitView();

		view.getUserSelect().addActionListener(new UserSelectListener());
		view.getExit().addActionListener(new ExitListener());

		view.getTablePanel().add( clientSimple.getView(), BorderLayout.CENTER );
	}

	public void showInitView() {
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
	}

	// Listener for UserSelect button
	private class UserSelectListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedRow = clientSimple.getView().getTable().getSelectedRow();

			if (selectedRow != -1)
			{
				System.out.println("Selected User: " + selectedRow);
				return;
			}

			System.out.println("No user selected");
		}
	}

	// Listener for Exit button
	private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
