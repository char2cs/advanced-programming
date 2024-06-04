package mateourrutia.Controller;

import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Esta clase habilita a sus hijos a ser:
 *  1. Un JFrame por ellos mismos.
 *  2. Ser un JPanel.
 *
 * Tambien se encarga de controllar los elementos dentro
 * de la vista Window.
 * @param <T>
 */
public abstract class WindowController<T extends JPanel> {
	private String windowTitle;
	private Window window;
	private T view;

	public WindowController() {}

	public WindowController(
			T panel
	) {
		setView(panel);
	}

	protected void setView(T view) {
		this.view = view;
	}

	public T getView() {
		return view;
	}

	public Window getWindow() {
		return window;
	}

	public void showWindow() {
		showWindow(JFrame.EXIT_ON_CLOSE);
	}

	public void showWindow(int operation) {
		if (window == null)
		{
			window = new Window();
			window.setTitle(windowTitle);
			window.getContentPane().add(view, BorderLayout.CENTER);
			initController();
		}

		window.setDefaultCloseOperation(operation);
		window.setVisible(true);
	}

	public void hideWindow() {
		window.setVisible(false);
	}

	public void repaint() {
		window.revalidate();
		window.repaint();

		view.revalidate();
		view.repaint();
	}

	public void onClose(Runnable action) {
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				action.run();
			}
		});
	}

	public void setTitle(String title) {
		windowTitle = title;
	}

	public void showMessageDialog(String message, int messageType) {
		JOptionPane.showMessageDialog(view, message, "UNFORESEEN CONSEQUENCES", messageType);
	}

	public void handleError(Exception ex) {
		ex.printStackTrace();
		ErrorController.show(view, ex);
	}

	/**
	 * Para ventanas emergentes que requieran, generalmente, recargar algun elemento.
	 * @param
	 */
	protected abstract class OpenAnotherWindowListener<object extends WindowController<? extends JPanel>> implements ActionListener {
		/**
		 * Este metodo deberia devolver el objecto CRUD ya creado para este Listener.
		 * @return Instancia de Object
		 */
		protected abstract object Object();

		/**
		 * Se ejecuta cuando la ventana emergente es cerrada.
		 */
		protected abstract void onClose();

		@Override
		public void actionPerformed(ActionEvent e) {
			object crud = Object();

			if ( crud == null )
				return;

			hideWindow();
			crud.showWindow(JFrame.DISPOSE_ON_CLOSE);

			crud.onClose(() -> {
				onClose();
				showWindow();
			});
		}
	}

	/**
	 * Especifico de WindowView
	 * En este caso, abre la ventana de TransactionHistory.
	 */
	private void initController() {
		getWindow().getTransactionHistory().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionHistorySimple transactionHistorySimple = new TransactionHistorySimple();
				transactionHistorySimple.showWindow(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	}
}
