package org.app;

import org.controller.ApplicationController;
import org.gui.frame.MainApplicationFrame;
import org.gui.menu.ApplicationMenuBar;
import org.gui.menu.MenuManager;

import java.awt.Frame;
import java.util.Locale;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class RobotsProgram {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        localizeApp();
        SwingUtilities.invokeLater(RobotsProgram::initializeApp);

    }
    private static void initializeApp() {
        ApplicationController controller = new ApplicationController();

        MenuManager menuManager = new MenuManager(controller);
        ApplicationMenuBar menuBar = new ApplicationMenuBar(menuManager);

        MainApplicationFrame frame = new MainApplicationFrame(menuBar);
        controller.setMainFrame(frame);
        controller.setLookAndViewUpdater(frame);

        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

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

}
