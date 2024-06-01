package mateourrutia;

import javax.swing.*;

import mateourrutia.Controller.InitController;

/**
 * TODO: Account CRUD, User menu and Account issues like, transfer, deposit, convert, etc.
 * 	Check TransactionHistorySimple if it works (It won't, so fix it.)
 * 	Account TODO: Create / Remove (Remove will work though)
 * 	Account TODO: Transfers and deposits.
 */

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
			initController.showWindow();
		});
	}
}