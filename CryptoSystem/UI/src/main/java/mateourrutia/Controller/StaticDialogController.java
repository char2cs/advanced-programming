package mateourrutia.Controller;

import mateourrutia.View.StaticDialogView;
import mateourrutia.View.Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class StaticDialogController {
	private StaticDialogView 	view;
	private Boolean 			userSelected 	= null;
	private CountDownLatch 		latch 			= new CountDownLatch(1);

	public StaticDialogController(
			Window owner
	) {
		view = new StaticDialogView(owner);

		getView().getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUserSelected(true);
				closeDialog();
			}
		});

		getView().getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUserSelected(false);
				closeDialog();
			}
		});

		view.setModal(true);
		view.pack();
		view.setVisible(true);
	}

	public StaticDialogController(
			Window owner,
			JPanel innerView
	) {
		this(owner);
		setInnerView(innerView);
	}

	public StaticDialogView getView() {
		return view;
	}

	public Boolean getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(Boolean userSelected) {
		this.userSelected = userSelected;
	}

	private void closeDialog() {
		view.dispose();
		latch.countDown();
	}

	public void setInnerView(JPanel innerView) {
		view.getContentPanel().removeAll();
		view.getContentPanel().add(innerView, BorderLayout.CENTER);
		view.getContentPanel().revalidate();
		view.getContentPanel().repaint();
	}

	public static boolean showStaticDialog(
			Window owner,
			JPanel innerView
	) {
		StaticDialogController staticDialogController = new StaticDialogController(owner, innerView);

		System.out.println( innerView );

		try {
			staticDialogController.latch.await();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		return staticDialogController.getUserSelected();
	}

}
