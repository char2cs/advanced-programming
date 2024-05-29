package mateourrutia.render.ButtonTable;

import javax.swing.*;

public abstract class ButtonTableRenderer {
	private ButtonEditor 	buttonEditor;
	private ButtonRenderer 	buttonRenderer;

	public ButtonTableRenderer() {
		buttonRenderer = new ButtonRenderer() {
			@Override
			public String getLabel(Object object) {
				return setButtonLabel(object);
			}
		};

		buttonEditor = new ButtonEditor() {
			@Override
			public String getLabel(Object object) {
				return setButtonLabel(object);
			}

			@Override
			public void onClick(Object object) {
				onClickPanel( object );
			}
		};
	}

	public ButtonEditor getButtonEditor() {
		return buttonEditor;
	}

	public ButtonRenderer getButtonRenderer() {
		return buttonRenderer;
	}

	public abstract String setButtonLabel(Object object);

	public abstract void onClickPanel(Object object);
}
