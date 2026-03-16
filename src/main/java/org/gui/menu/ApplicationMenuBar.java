package org.gui.menu;

import javax.swing.*;


public class ApplicationMenuBar extends JMenuBar {

    public ApplicationMenuBar(MenuManager menuManager) {
        add(menuManager.createFileMenu());
        add(menuManager.createLookAndFeelMenu());
        add(menuManager.createTestMenu());
    }
}
