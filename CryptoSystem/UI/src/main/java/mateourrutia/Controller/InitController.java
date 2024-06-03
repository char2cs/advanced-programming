package mateourrutia.Controller;

import mateourrutia.Controller.Tables.ClientCRUD;
import mateourrutia.Controller.Tables.ClientSimple;
import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.Domain.Client;
import mateourrutia.Services.ClientService;
import mateourrutia.View.InitView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InitController extends WindowController<InitView> {
	private ClientService clientService;
	private ClientSimple clientSimple;

	public InitController(
			ClientService clientService
	) {
		super( new InitView() );
		this.clientService = clientService;

		setTitle("Crypto System");

		getView().getAllClients().addActionListener(new OpenAnotherWindowListener<ClientCRUD>() {
			@Override
			protected ClientCRUD Object() {
				return new ClientCRUD( clientService );
			}

			@Override
			protected void onClose() {
				reloadTable();
			}
		});

		getView().getTransactionHistory().addActionListener(new OpenAnotherWindowListener<TransactionHistorySimple>() {
			@Override
			protected TransactionHistorySimple Object() {
				return new TransactionHistorySimple();
			}

			@Override
			protected void onClose() {
				reloadTable();
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
			List<Client> clients = clientService.getClients();
			int row = clientSimple.getSelectedRowIndex();

			if (row == -1)
				return null;

			return new ClientOverviewController(
					clients.get(row),
					clientService
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
