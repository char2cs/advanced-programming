package mateourrutia;

import javax.swing.*;

import mateourrutia.Controller.InitController;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			InitController initController = new InitController();
			initController.showInitView();
		});
	}
}