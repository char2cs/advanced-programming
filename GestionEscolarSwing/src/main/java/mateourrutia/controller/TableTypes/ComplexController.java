package mateourrutia.controller.TableTypes;

import mateourrutia.view.TableTypes.ComplexView;

public abstract class ComplexController<T, G> {
	private ComplexView 			view;
	private GeneralController<T> 	generalController;
	private SimpleController<G>		simpleController;

	public ComplexController() {}

	public ComplexController(
			GeneralController<T> generalController,
			SimpleController<G> simpleController
	) {
		setControllers(generalController, simpleController);
	}

	protected void setControllers(
			GeneralController<T> generalController,
			SimpleController<G> simpleController
	) {
		this.generalController 	= generalController;
		this.simpleController 	= simpleController;

		this.view 		= new ComplexView(
				generalController.getView(),
				simpleController.getView()
		);
	}

	public ComplexView getView() {
		return view;
	}
}
