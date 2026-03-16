package org.gui.menu;

import org.controller.ApplicationController;
import org.controller.actions.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuManager {
    private final ApplicationController controller;

    public MenuManager(ApplicationController controller) {
        this.controller = controller;
    }

    public JMenu createFileMenu() {
        return new MenuBuilder(
                    "Файл",
                    KeyEvent.VK_F,
                    "Команды управления файлом")
                .item(new ExitAction(controller))
                .build();
    }

    public JMenu createLookAndFeelMenu() {
        return new MenuBuilder(
                    "Режим отображения",
                    KeyEvent.VK_V,
                    "Управление режимом отображения приложения")
                .item(new SystemLookAndFeelAction(controller))
                .item(new CrossplatformLookAndFeelAction(controller))
                .build();
    }

    public JMenu createTestMenu() {
        return new MenuBuilder(
                    "Тесты",
                    KeyEvent.VK_T,
                    "Тестовые команды")
                .item(new LogMessageAction(controller))
                .item(new OpenFileAction(controller))
                .build();
    }

    private static class MenuBuilder {
        private final JMenu menu;

        MenuBuilder(String title, int mnemonic, String description) {
            menu =  new JMenu(title);
            menu.setMnemonic(mnemonic);
            menu.getAccessibleContext().setAccessibleDescription(description);
        }

        MenuBuilder item(Action action) {
            menu.add(new JMenuItem(action));
            return this;
        }

        JMenu build() {
            return menu;
        }
    }
}
