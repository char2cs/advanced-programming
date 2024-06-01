package mateourrutia.Controller.Domain;

import mateourrutia.Controller.TableTypes.GeneralTable;
import mateourrutia.Domain.Client;

import javax.swing.*;
import javax.swing.event.TableModelEvent;

/**
 * TODO!!!!
 */

public class ClientCRUD extends GeneralTable<Client> {

	public ClientCRUD() {}

	@Override
	protected Client onAdd() throws ClassCastException {
		return null;
	}

	@Override
	protected void onUpdate(TableModelEvent e) throws ClassCastException {

	}

	@Override
	protected void onDelete(Integer[] rows, JTable table) throws ClassCastException {

	}
}
