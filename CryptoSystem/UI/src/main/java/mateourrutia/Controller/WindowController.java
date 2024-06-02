package mateourrutia.Controller;

import mateourrutia.View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Esta clase habilita a sus hijos a ser:
 *  1. Un JFrame por ellos mismos.
 *  2. Ser un JPanel.
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
}
