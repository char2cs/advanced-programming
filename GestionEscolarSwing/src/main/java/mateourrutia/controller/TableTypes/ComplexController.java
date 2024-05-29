package mateourrutia.controller.TableTypes;

import mateourrutia.domain.Clase;
import mateourrutia.view.ObjectView;
import mateourrutia.view.TableTypes.ComplexView;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public abstract class ComplexController<T, G> {
	private Window					window;
	private ComplexView 			view;
	private GeneralController<T> 	generalController;
	private GeneralController<G>	simpleController;

	public ComplexController() {}

	public ComplexController(Window window) {
		this.window = window;
	}

	public ComplexController(
			Window 					window,
			GeneralController<T> 	generalController,
			GeneralController<G> 	simpleController
	) {
		this.window = window;
		setControllers( generalController, simpleController );
	}

	protected void setControllers(
			GeneralController<T> generalController,
			GeneralController<G> simpleController
	) {
		this.generalController 	= generalController;
		this.simpleController 	= simpleController;

		this.view = new ComplexView(
				generalController.getView(),
				simpleController.getView()
		);
	}

	public ComplexView getView() {
		return view;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
}
