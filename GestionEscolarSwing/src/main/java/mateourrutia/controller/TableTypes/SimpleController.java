package mateourrutia.controller.TableTypes;

import mateourrutia.view.TableTypes.SimpleView;

import javax.swing.table.DefaultTableModel;

public abstract class SimpleController<T> extends TableTypes<T> {
	private SimpleView view;

	public SimpleController() {}

	public SimpleController(
			DefaultTableModel model
	) {
		setModel(model);
	}

	protected void setModel(DefaultTableModel model) {
		this.model 	= model;
		this.view 	= new SimpleView( this.model );
	}

	public SimpleView getView() {
		return view;
	}
}
