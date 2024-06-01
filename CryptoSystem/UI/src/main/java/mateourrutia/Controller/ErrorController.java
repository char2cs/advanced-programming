package mateourrutia.Controller;

import javax.swing.*;

public class ErrorController {
	public static void show(
			java.awt.Component component,
			Exception e
	) {
		JOptionPane.showMessageDialog(
				component,
				e.getMessage(),
				"UNFORESEEN CONSEQUENCES",
				JOptionPane.ERROR_MESSAGE
		);
	}
}
