package mateourrutia.Controller;

import mateourrutia.View.StaticDialogView;
import mateourrutia.View.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Semaphore;

/**
 * Dialog basico.
 *
 * Siempre que se instancie, se tiene que tener en cuenta que
 * el main frame se pausara, y recien volvera a activarse una
 * vez que este dialog se cierre.
 */
public abstract class StaticDialogController<T extends JPanel> {
	private Window parent;
	private Semaphore semaphore;
	private StaticDialogView view;

	private T innerView;

	public StaticDialogController(Window owner) {
		this(owner, null);
	}

	public StaticDialogController(Window owner, T innerView) {
		parent = owner;
		this.innerView 	= innerView;
		semaphore 		= new Semaphore(0);
		view 			= new StaticDialogView(owner);
		setInnerView(innerView);

		view.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAccept();
				closeDialog();
			}
		});

		view.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
				closeDialog();
			}
		});

		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onCancel();
				closeDialog();
			}
		});

		view.setModal(true);
		view.pack();
	}

	public abstract void onAccept();
	public abstract void onCancel();

	public void showDialog() {
		view.setVisible(true);
		try {
			semaphore.acquire(); // Pausamos el Main Thread hasta que tengamos una respuesta.
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			ErrorController.show(view, e);
		}
	}

	public StaticDialogView getView() {
		return view;
	}

	public void closeDialog() {
		view.dispose();
		parentWindowActivate();
		semaphore.release(); // Liberamos el Main Thread.
	}

	private void parentWindowActivate() {
		parent.revalidate();
		parent.repaint();
	}

	public void setInnerView(T innerView) {
		this.innerView = innerView;
		view.getContentPanel().removeAll();
		view.getContentPanel().add(innerView, BorderLayout.CENTER);
		view.getContentPanel().revalidate();
		view.getContentPanel().repaint();
	}

	public T getInnerView() {
		return innerView;
	}
}
