package mateourrutia.Controller;

import mateourrutia.Controller.Tables.TransactionHistorySimple;
import mateourrutia.Domain.Currency;
import mateourrutia.Domain.TransactionHistory;
import mateourrutia.Service.TransactionHistoryService;
import mateourrutia.View.TransactionHistoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionHistoryController extends WindowController<TransactionHistoryView> {
	private final 	TransactionHistoryService 	transactionHistoryService;
	private 		TransactionHistorySimple	transactionHistorySimple;

	/**
	 * Variables para el filtro.
	 */
	private TransactionHistory.Status 	status		= TransactionHistory.Status.ALL;
	private TransactionHistory.Type 	type		= TransactionHistory.Type.ALL;
	private Long 						cbu			= 0L;
	private double 						balanceMin 	= 0;
	private double 						balanceMax 	= 0;

	public TransactionHistoryController(
			TransactionHistoryView 		view,
			TransactionHistoryService 	transactionHistoryService
	) {
		super( view );
		this.transactionHistoryService = transactionHistoryService;
		this.transactionHistorySimple = new TransactionHistorySimple(
				transactionHistoryService.getAll()
		);

		initComponents();

		transactionHistoryService.listener( this::reloadTable );
		reloadTable();
	}

	/**
	 * Se encarga de recargar la tabla con los nuevos filtros, siempre y cuando se necesite.
	 */
	private void reloadTable() {
		transactionHistorySimple.setModel(transactionHistoryService.getAll(
				this.status,
				this.type,
				this.cbu,
				this.balanceMin,
				this.balanceMax
		));
		getView().setContent(transactionHistorySimple.getView());
	}

	private void initComponents() {
		getView().setContent(transactionHistorySimple.getView());

		getView().getState().setModel( new DefaultComboBoxModel<>( TransactionHistory.Status.values() ) );
		getView().getState().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				status = TransactionHistory.Status.values()[ getView().getState().getSelectedIndex() ];
				reloadTable();
			}
		});

		getView().getType().setModel( new DefaultComboBoxModel<>( TransactionHistory.Type.values() ) );
		getView().getType().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				type = TransactionHistory.Type.values()[ getView().getType().getSelectedIndex() ];
				reloadTable();
			}
		});

		/**
		 * Estos dos actionListener solo afectan a los filtros en si, no
		 * a Type o State.
		 */

		getView().getReset().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbu			= 0L;
				balanceMin 	= 0;
				balanceMax 	= 0;
				reloadTable();
			}
		});

		getView().getFilter().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String balanceMaxValueStr = getView().getBalanceMax().getText();
					String balanceMinValueStr = getView().getBalanceMin().getText();
					String cbuText = getView().getCBUText().getText();

					if (!balanceMaxValueStr.isEmpty())
						if (isValidDouble(balanceMaxValueStr))
							setBalanceMax(Double.parseDouble(balanceMaxValueStr));
						else
							throw new NumberFormatException("Invalid balanceMax value");

					if (!balanceMinValueStr.isEmpty())
						if (isValidDouble(balanceMinValueStr))
							setBalanceMin(Double.parseDouble(balanceMinValueStr));
						else
							throw new NumberFormatException("Invalid balanceMin value");

					if (!cbuText.isEmpty())
						if (isValidLong(cbuText))
							setCbu( Long.parseLong(cbuText) );
						else
							throw new NumberFormatException("Invalid CBU value");

					reloadTable();
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(
							getView(),
							"Solo numeros son validos en los filtros...",
							"UNFORESEEN CONSEQUENCES",
							JOptionPane.ERROR_MESSAGE
					);
				}
				catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(
							getView(),
							"Error procesando los filtros: " + ex.getMessage(),
							"UNFORESEEN CONSEQUENCES",
							JOptionPane.ERROR_MESSAGE
					);
				}

			}
		});
	}

	protected boolean isValidDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean isValidLong(String value) {
		try {
			Long.parseLong(value);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	private DefaultComboBoxModel<String> getAccountTypes() {
		DefaultComboBoxModel<String> accountTypes = new DefaultComboBoxModel<>();

		accountTypes.addElement("All");
		accountTypes.addElement("CheckingAccount");
		accountTypes.addElement("SavingAccount");

		for (Currency currency : Currency.values())
			accountTypes.addElement( "Wallet en " + currency );

		return accountTypes;
	}

	public Long getCbu() {
		return cbu;
	}

	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}

	public double getBalanceMin() {
		return balanceMin;
	}

	public void setBalanceMin(double balanceMin) {
		this.balanceMin = balanceMin;
	}

	public double getBalanceMax() {
		return balanceMax;
	}

	public void setBalanceMax(double balanceMax) {
		this.balanceMax = balanceMax;
	}
}
