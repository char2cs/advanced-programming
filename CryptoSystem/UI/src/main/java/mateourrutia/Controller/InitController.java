package mateourrutia.Controller;

import mateourrutia.Controller.Tables.ClientCRUD;
import mateourrutia.Controller.Tables.ClientSimple;
import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.Domain.Client;
import mateourrutia.Service.ClientService;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.ClientOverviewView;
import mateourrutia.View.InitView;
import mateourrutia.View.TransactionHistoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InitController extends WindowController<InitView> {
	private ClientService 				clientService;
	private ClientSimple 				clientSimple;
	private TransactionHistoryService 	transactionHistoryService;

	public InitController(
			InitView view,
			ClientService clientService,
			TransactionHistoryService transactionHistoryService
	) {
		super( view );
		this.clientService = clientService;
		this.transactionHistoryService = transactionHistoryService;

		setTitle("Crypto System");

		getView().getAllClients().addActionListener(new OpenAnotherWindowListener<ClientCRUD>() {
			@Override
			protected ClientCRUD Object() {
				return new ClientCRUD( clientService, transactionHistoryService );
			}

			@Override
			protected void onClose() {
				reloadTable();
			}
		});

		getView().getTransactionHistory().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionHistoryController transactionHistoryController = new TransactionHistoryController(
						new TransactionHistoryView(),
						transactionHistoryService
				);

				transactionHistoryController.showWindow(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		getView().getUserSelect().addActionListener(new UserSelectListener());
		getView().getExit().addActionListener(new ExitListener());

		reloadTable();
	}

	private void reloadTable() {
		clientSimple = new ClientSimple( clientService );
		getView().getTablePanel().removeAll();
		getView().getTablePanel().add( clientSimple.getView(), BorderLayout.CENTER );
	}

	private class UserSelectListener extends OpenAnotherWindowListener<ClientOverviewController> {
		@Override
		protected ClientOverviewController Object() {
			List<Client> clients = clientService.getAll();
			int row = clientSimple.getSelectedRowIndex();

			if (row == -1)
				return null;

			return new ClientOverviewController(
					new ClientOverviewView(),
					clients.get(row),
					clientService,
					transactionHistoryService
			);
		}

		@Override
		protected void onClose() {
			reloadTable();
			showWindow();
		}
	}

	private class ExitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public ClientSimple getClientSimple() {
		return clientSimple;
	}
}
