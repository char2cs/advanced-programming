package mateourrutia.Controller;

import mateourrutia.Controller.Tables.ClientCRUD;
import mateourrutia.Controller.Tables.ClientSimple;
import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.Domain.Client;
import mateourrutia.Services.ClientService;
import mateourrutia.View.InitView;

import javax.swing.*;
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
		});

		getView().getTransactionHistory().addActionListener(new OpenAnotherWindowListener<TransactionHistorySimple>() {
			@Override
			protected TransactionHistorySimple Object() {
				return new TransactionHistorySimple();
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

	/**
	 * Para ventanas emergentes que requieran recargar el contenido de Init.
	 * @param
	 */
	private abstract class OpenAnotherWindowListener<object extends WindowController<? extends JPanel>> implements ActionListener {
		/**
		 * Este metodo deberia devolver el objecto CRUD ya creado para este Listener.
		 * @return Instancia de Object
		 */
		protected abstract object Object();

		@Override
		public void actionPerformed(ActionEvent e) {
			hideWindow();

			object crud = Object();
			crud.showWindow(JFrame.DISPOSE_ON_CLOSE);

			crud.onClose(() -> {
				reloadTable();
				showWindow();
			});
		}
	}

	// Listener for UserSelect button
	private class UserSelectListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<Client> clients = clientService.getClients();
			int row = clientSimple.getSelectedRowIndex();

			if (row == -1)
				return;

			ClientOverviewController clientOverviewController = new ClientOverviewController(
					clients.get(row),
					clientService
			);

			hideWindow();
			clientOverviewController.showWindow(JFrame.DISPOSE_ON_CLOSE);
			clientOverviewController.onClose(() -> {
				reloadTable();
				showWindow();
			});
		}
	}

	// Listener for Exit button
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
