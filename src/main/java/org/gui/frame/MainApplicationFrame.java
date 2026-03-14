package org.gui.frame;

import org.controller.ApplicationController;
import org.gui.menu.ApplicationMenuBar;
import org.gui.manager.InternalWindowManager;
import org.gui.view.View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainApplicationFrame extends JFrame implements View {
    private static final int FRAME_INSET = 50;

    private final JDesktopPane desktopPane = new JDesktopPane();
    private final ApplicationController controller;
    
    public MainApplicationFrame(ApplicationController controller) {
        this.controller = controller;
        setJMenuBar(new ApplicationMenuBar(controller));

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

    @Override
    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
    }

//    protected JMenuBar createMenuBar() {
//        JMenuBar menuBar = new JMenuBar();
// 
//        //Set up the lone menu.
//        JMenu menu = new JMenu("Document");
//        menu.setMnemonic(KeyEvent.VK_D);
//        menuBar.add(menu);
// 
//        //Set up the first menu item.
//        JMenuItem menuItem = new JMenuItem("New");
//        menuItem.setMnemonic(KeyEvent.VK_N);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("new");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        //Set up the second menu item.
//        menuItem = new JMenuItem("Quit");
//        menuItem.setMnemonic(KeyEvent.VK_Q);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("quit");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        return menuBar;
//    }

}
