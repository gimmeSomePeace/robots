package org.app;

import org.controller.ApplicationController;
import org.gui.frame.MainApplicationFrame;
import org.gui.internal.GameWindow;
import org.gui.internal.LogWindow;
import org.gui.manager.InternalWindowManager;
import org.gui.menu.ApplicationMenuBar;
import org.gui.menu.MenuManager;
import org.service.Logger;
import org.service.state.PreferencesWindowStateService;

import java.awt.*;
import java.util.List;
import java.util.Locale;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 * Главный класс приложения.
 * Запускает Swing GUI в EDT
 */
public class RobotsProgram {
    /**
     * Точка входа в приложение.
     * Инициализирует окружение, локализацию, создает главное окно приложения.
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        setDefaultLookAndFeel();
        localizeApp();
        SwingUtilities.invokeLater(RobotsProgram::initializeApp);
    }

    /**
     * Инициализирует главное окно приложения через DI. Выполняет роль composition root.
     * Создаёт контроллер, меню, внутренние окна и связывает их с MainApplicationFrame.
     * Метод вызывается внутри EDT через SwingUtilities.invokeLater.
     */
    private static void initializeApp() {
        ApplicationController controller = new ApplicationController();
        PreferencesWindowStateService stateService = new PreferencesWindowStateService();

        MenuManager menuManager = new MenuManager(controller);
        ApplicationMenuBar menuBar = new ApplicationMenuBar(menuManager);
        InternalWindowManager windowManager = new InternalWindowManager(
                List.of(
                    createGameWindow(),
                    createLogWindow()
                ),
                stateService
        );

        MainApplicationFrame frame = new MainApplicationFrame(
                menuBar,
                windowManager,
                controller
        );
        controller.setView(frame);

        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        // возвращаем состояние окон с последней сессии.
        windowManager.restoreState();
    }

    /**
     * Устанавливает дефолтные настройки для внешнего вида окон приложения
     */
    private static void setDefaultLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Couldn't set default look and feel");
        }
    }

    /**
     * Настраивает локализацию для приложения
     */
    private static void localizeApp() {

        Locale.setDefault(Locale.of("ru"));

        UIManager.put("OptionPane.yesButtonText", "Да");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.okButtonText", "Oк");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.openButtonToolTipText", "Открыть выбранный файл");
        UIManager.put("FileChooser.saveButtonText", "Сохранить");
        UIManager.put("FileChooser.saveButtonToolTipText", "Сохранить файл");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Отменить выбор");
        UIManager.put("FileChooser.directoryDescriptionText", "Папка");
        UIManager.put("FileChooser.fileDescriptionText", "Файл");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.fileNameLabelText", "Имя файла:");
        UIManager.put("FileChooser.folderNameLabelText", "Имя папки:");
        UIManager.put("FileChooser.lookInLabelText", "Папка:");
        UIManager.put("FileChooser.saveInLabelText", "Сохранить в:");
        UIManager.put("FileChooser.upFolderToolTipText", "На уровень вверх");
        UIManager.put("FileChooser.homeFolderToolTipText", "Домашняя папка");
        UIManager.put("FileChooser.newFolderToolTipText", "Создать папку");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Список");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Таблица");
    }

    /**
     * Создает и настраивает окно логов {@link LogWindow}
     * @return новый экземпляр {@link LogWindow}
     */
    private static LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setName("logWindow");
        // Значения по умолчанию
        logWindow.setLocation(10, 10);
        logWindow.setPreferredSize(new Dimension(300, 200));
        logWindow.pack();

        Logger.debug("Протокол работает");

        return logWindow;
    }

    /**
     * Создает и настраивает окно игры {@link GameWindow}
     * @return новый экземпляр {@link GameWindow}
     */
    private static GameWindow createGameWindow() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setName("gameWindow");
        // Значение по умолчанию
        gameWindow.setSize(400, 400);
        return gameWindow;
    }
}
