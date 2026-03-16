package org.controller.actions;

import org.controller.ApplicationController;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class OpenFileAction extends AbstractAction {
    private final ApplicationController controller;

    public OpenFileAction(ApplicationController controller) {
        super("Открытие файла");
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        // Реализация просто для демонстрации
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
    }
}
