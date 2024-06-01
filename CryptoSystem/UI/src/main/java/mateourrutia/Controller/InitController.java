package mateourrutia.Controller;

import mateourrutia.Controller.Tables.ClientCRUD;
import mateourrutia.Controller.Tables.ClientSimple;
import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.DAO.ClientDAO;
import mateourrutia.Domain.Client;
import mateourrutia.Exceptions.ObjectNotFoundException;
import mateourrutia.Factory.ClientFactory;
import mateourrutia.View.InitView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InitController extends WindowController<InitView> {
	private ClientSimple clientSimple;

	public InitController() {
		super( new InitView() );
		setTitle("Crypto System");

		getView().getAllClients().addActionListener(new OpenAnotherWindowListener<ClientCRUD>() {
			@Override
			protected ClientCRUD Object() {
				return new ClientCRUD();
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
		clientSimple = new ClientSimple();
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
			ClientDAO clientDAO = ClientFactory.getClientDAO("FileWriter");

			List<Client> clients = clientDAO.getAll();
			int row = clientSimple.getSelectedRowIndex();

			if (row == -1)
				return;

			Client client;

			try {
				client = clientDAO.get( clients.get(row).getUuid() );
			}
			catch (ObjectNotFoundException ex) {
				ex.printStackTrace();
				ErrorController.show( getView(), ex );
				return;
			}

			ClientOverviewController clientOverviewController = new ClientOverviewController(client);

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
