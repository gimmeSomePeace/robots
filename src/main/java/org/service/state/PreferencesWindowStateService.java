package org.service.state;

import javax.swing.*;
import java.util.prefs.Preferences;


public class PreferencesWindowStateService implements WindowStateService {
    private final Preferences prefs = Preferences.userNodeForPackage(PreferencesWindowStateService.class);

    @Override
    public void save(JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            String key = frame.getName();
            if (key == null) continue;

            prefs.putInt(key + "_x", frame.getX());
            prefs.putInt(key + "_y", frame.getY());
            prefs.putInt(key + "_width", frame.getWidth());
            prefs.putInt(key + "_height", frame.getHeight());
        }
    }

    @Override
    public void restore(JDesktopPane desktopPane) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            String key = frame.getName();
            if (key == null) continue;

            int x = prefs.getInt(key + "_x", frame.getX());
            int y = prefs.getInt(key + "_y", frame.getY());
            int width = prefs.getInt(key + "_width", frame.getWidth());
            int height = prefs.getInt(key + "_height", frame.getHeight());

            frame.setBounds(x, y, width, height);
        }
    }
}
