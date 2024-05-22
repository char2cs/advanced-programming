package mateourrutia.controller;

import javax.swing.*;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import mateourrutia.view.InitView;

public class InitController {
	private InitView initView;

	public InitController(Window owner) {
		initView = new InitView(owner);
		setUpListeners();
	}

	private void setUpListeners() {
		initView.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onOk();
			}
		});

		initView.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

		initView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});
	}

	private void onOk() {
		initView.dispose();
		ObjectController objectController = new ObjectController(
				(String) initView.getComboBox1().getSelectedItem()
		);
		objectController.showObjectView();
	}

	private void onCancel() {
		// Handle Cancel button action
		initView.dispose();
	}

	public void showInitView() {
		initView.pack();
		initView.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			InitController initController = new InitController(null);
			initController.showInitView();
		});
	}
}