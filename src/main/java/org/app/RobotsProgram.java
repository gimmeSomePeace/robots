package org.app;

import org.controller.ApplicationController;
import org.gui.frame.MainApplicationFrame;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class RobotsProgram
{
    public static void main(String[] args) {
      try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        ApplicationController controller = new ApplicationController();
        MainApplicationFrame frame = new MainApplicationFrame(controller);
        controller.setLookAndViewUpdater(frame);

        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }}
