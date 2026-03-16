package org.gui.frame;

import org.controller.ApplicationController;
import org.gui.menu.ApplicationMenuBar;
import org.gui.manager.InternalWindowManager;
import org.gui.view.View;
import org.gui.internal.GameWindow;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JInternalFrame;


public class MainApplicationFrame extends JFrame implements View {
    private static final int FRAME_INSET = 50;

    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame(
            ApplicationMenuBar menuBar
    ) {
        setJMenuBar(menuBar);

        setUpFrameBounds();
        setContentPane(desktopPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        InternalWindowManager windowManager = new InternalWindowManager(desktopPane);
        windowManager.initializeWindows();
    }

    private void setUpFrameBounds() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(
                FRAME_INSET,
                FRAME_INSET,
                screenSize.width  - FRAME_INSET * 2,
                screenSize.height - FRAME_INSET * 2
        );
    }

    public void shutdown() {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof GameWindow) {
                ((GameWindow) frame).getVisualizer().shutdown();
            }
        }
    }

    @Override
    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
