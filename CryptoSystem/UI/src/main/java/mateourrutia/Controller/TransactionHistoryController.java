package mateourrutia.Controller;

import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.TransactionHistoryView;

import javax.swing.*;

/**
 * TODO Filters...
 */
public class TransactionHistoryController extends WindowController<TransactionHistoryView> {
	private final 	TransactionHistoryService 	transactionService;
	private 		TransactionHistorySimple	transactionHistorySimple;

	public TransactionHistoryController(
			TransactionHistoryView 		view,
			TransactionHistoryService 	transactionHistoryService
	) {
		super( view );
		this.transactionService = transactionHistoryService;
		this.transactionHistorySimple = new TransactionHistorySimple(
				transactionHistoryService.getAll()
		);

		initComponents();

		/**
		 * Actualizamos la tabla cada vez que tengamos algun cambio.
		 *
		 * TODO: Update table keeping the filters on.
		 */
		transactionService.listener(() -> {
			transactionHistorySimple.setModel(
					transactionHistoryService.getAll()
			);

			getView().setContent(transactionHistorySimple.getView());
		});
	}

	private void initComponents() {
		getView().setContent(transactionHistorySimple.getView());

		getView().getState().setModel( getStateComboModel() );
		getView().getType().setModel( getTypeComboModel() );
	}

	private DefaultComboBoxModel getStateComboModel() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();

		model.addElement("Todas");

		for ( TransactionHistory.Status status : TransactionHistory.Status.values() )
			model.addElement( status.name() );

		return model;
	}

	private DefaultComboBoxModel getTypeComboModel() {
		DefaultComboBoxModel model = new DefaultComboBoxModel();

		model.addElement("Todos");

		for ( TransactionHistory.Type status : TransactionHistory.Type.values() )
			model.addElement( status.name() );

		return model;
	}
}
