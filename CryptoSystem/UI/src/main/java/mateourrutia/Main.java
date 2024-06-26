package mateourrutia;

import javax.swing.*;

import mateourrutia.Controller.InitController;
import mateourrutia.Factory.AccountFactory;
import mateourrutia.Factory.ClientFactory;
import mateourrutia.Factory.PersistenceType;
import mateourrutia.Factory.TransactionHistoryFactory;
import mateourrutia.Service.ClientService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.InitView;

public class Main {
	public static void main(String[] args) {
		/**
		 * Cargar en memoria todos los usuarios
		 *
		 * El tipo de persistencia DEBE ser del mismo tipo en ambos casos.
		 * Ver mas en: Clase 'Client' del Domain.
		 */
		ClientService clientService = new ClientService(
				ClientFactory.getClientDAO(PersistenceType.FILEWRITER),
				AccountFactory.getAccountDAO(PersistenceType.FILEWRITER)
		);

		/**
		 * Esperamos a que cargen todos los datos de todos los clientes
		 */
		clientService.coldStart();

		/**
		 * Escucha continuamente por cambios en el archivo de clientes.
		 *
		 * Esto se debia a una inconsistencia dentro del sistema de servicios.
		 * Relacionada a como se actualizaban las listas internas de cada servicio,
		 * tanto del cliente y de cuentas.
		 *
		 * Se deja para depurar a futuro, pero suele producir inconsistencias.
		 */
//		clientService.listener();

		/**
		 * Iniciamos el servicio de TransactionHistory
		 */
		TransactionHistoryService transactionHistoryService = new TransactionHistoryService(
				TransactionHistoryFactory.getTransactionHistoryDAO(PersistenceType.STRINGWRITER)
		);

		/**
		 * UI Stuff
		 */
		try {
			/**
			 * Depende el sistema, crashea o no.
			 */
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> {
			InitController initController = new InitController(
					new InitView(),
					clientService,
					transactionHistoryService
			);
			initController.showWindow();
		});

	}
}