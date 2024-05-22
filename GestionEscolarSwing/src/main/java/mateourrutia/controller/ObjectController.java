package mateourrutia.controller;

import mateourrutia.controller.Objects.PabellonController;
import mateourrutia.view.General.GeneralView;
import mateourrutia.view.ObjectView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class ObjectController {
	private final ObjectView objectView;

	public ObjectController(String selectedItem) {
		objectView = new ObjectView();
		setUpListeners();

		objectView.setScene( getUserSelection(selectedItem) );
	}

	private JPanel getUserSelection(
			String selectedItem
	) {
		switch (selectedItem) {
			case "Pabellon":
				return PabellonController.get().getView();
		}

		return null;
	}

	private void setUpListeners() {
		objectView.getExit().addActionListener(e -> onExit());

		objectView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onExit();
			}
		});

		// JTree Selection
		objectView.getEntities().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				onTreeSelectionChanged();
			}
		});
	}

	private void onExit() {
		objectView.dispose();
	}

	public void showObjectView() {
		objectView.setVisible(true);
	}

	private void onTreeSelectionChanged() {
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) objectView.getEntities().getLastSelectedPathComponent();

		if (selectedNode != null)
			objectView.setScene( getUserSelection( selectedNode.getUserObject().toString() ) );

	}

}