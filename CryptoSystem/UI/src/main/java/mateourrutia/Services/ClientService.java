package mateourrutia.Services;

import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Client Service es el encargado de cargar los datos del cliente
 * en runtime. A su vez, es la fuente principal donde se consultan
 * los datos del cliente.
 */
public class ClientService implements Runnable {
	private final ClientDAO clientDAO;
	private List<Client> 	clients = new ArrayList<>();

	public ClientService(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public List<Client> getClients() {
		return clients;
	}

	protected void loadClients() {
		Logger.log(Logger.LogLevel.INFO, "Loading Client Data...");
		clients = clientDAO.getAll();
		Logger.log(Logger.LogLevel.SUCCESS, "Client data loaded successfully!");
	}

	public Thread listener() {
		Thread thread = new Thread( new ClientMonitorFile(this) );
		thread.start();

		return thread;
	}

	@Override
	public void run() {
		loadClients();
	}

	private class ClientMonitorFile implements Runnable {
		private final 	String 			filePath = clientDAO.getFilePath();
		private 		long 			lastCheckedTime;
		private final 	ClientService 	clientService;

		public ClientMonitorFile(ClientService clientService) {
			this.clientService = clientService;
		}

		private void checkForFileChanges() {
			File file = new File(filePath);
			long lastModified = file.lastModified();

			if (lastModified > lastCheckedTime)
			{
				System.out.println("Detected changes in the data file. Reloading client data...");
				clientService.loadClients();
				lastCheckedTime = lastModified;
			}
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted())
			{
				checkForFileChanges();
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}

			return;
		}
	}
}
