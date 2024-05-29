package mateourrutia.controller.TableTypes;

import mateourrutia.domain.Clase;
import mateourrutia.view.TableTypes.ComplexView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class ComplexController<T, G> {
	private ComplexView 			view;
	private GeneralController<T> 	generalController;
	private GeneralController<G>	simpleController;

	public ComplexController() {}

	public ComplexController(
			GeneralController<T> generalController,
			GeneralController<G> simpleController
	) {
		setControllers(generalController, simpleController);
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
}
