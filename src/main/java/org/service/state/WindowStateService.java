package org.service.state;

import javax.swing.*;

public interface WindowStateService {
    void save(JDesktopPane desktopPane);
    void restore(JDesktopPane desktopPane);
}
