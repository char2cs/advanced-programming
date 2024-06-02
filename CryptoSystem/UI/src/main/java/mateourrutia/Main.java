package mateourrutia;

import javax.swing.*;

import mateourrutia.Controller.InitController;
import mateourrutia.Factory.ClientFactory;
import mateourrutia.Factory.PersistenceType;
import mateourrutia.Services.ClientService;

/**
 * TODO: Account CRUD, User menu and Account issues like, transfer, deposit, convert, etc.
 * 	Check TransactionHistorySimple if it works (It won't, so fix it.)
 * 	Account TODO: Create / Remove (Remove will work though)
 * 	Account TODO: Transfers and deposits.
 */

public class Main {
	public static void main(String[] args) {
		/**
		 * Cargar en memoria todos los usuarios
		 */
		ClientService clientService = new ClientService( ClientFactory.getClientDAO(PersistenceType.FILEWRITER) );
		Thread clientServiceThread = new Thread( clientService );
		clientServiceThread.start();

		try {
			clientServiceThread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		clientService.listener();

		/**
		 * UI Stuff
		 */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			InitController initController = new InitController( clientService );
			initController.showWindow();
		});
	}
}