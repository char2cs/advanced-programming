package mateourrutia.Controller;

import mateourrutia.Controller.Domain.ClientCRUD;
import mateourrutia.Controller.Domain.ClientSimple;
import mateourrutia.Controller.Domain.TransactionHistorySimple;
import mateourrutia.View.InitView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitController extends WindowController<InitView> {
	private ClientSimple clientSimple;

	public InitController() {
		super( new InitView() );

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
	 * @param <T>
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
			int selectedRow = clientSimple.getView().getTable().getSelectedRow();

			if (selectedRow != -1)
			{
				System.out.println("Selected User: " + selectedRow);
				return;
			}

			System.out.println("No user selected");
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
