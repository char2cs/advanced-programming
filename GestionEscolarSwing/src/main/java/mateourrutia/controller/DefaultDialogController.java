package mateourrutia.controller;

import mateourrutia.view.DefaultDialogView;
import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefaultDialogController {
    private DefaultDialogView dialogView;

    public DefaultDialogController(Window owner) {
        dialogView = new DefaultDialogView(owner);
        initController();
    }

    private void initController() {
        dialogView.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOk();
            }
        });

        dialogView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    public void openDialog() {
        dialogView.setVisible(true);
    }

    private void onOk() {
        // Perform actions when OK is pressed
        System.out.println("OK button pressed");
        dialogView.dispose();
    }

    private void onCancel() {
        // Perform actions when Cancel is pressed
        System.out.println("Cancel button pressed");
        dialogView.dispose();
    }
}