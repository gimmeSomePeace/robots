package org.service;

import org.service.logging.LogBuffer;
import org.model.LogLevel;

public final class Logger
{
    private static final LogBuffer defaultLogSource;
    static {
        defaultLogSource = new LogBuffer(100);
    }
    
    private Logger()
    {
    }

    public static void debug(String strMessage)
    {
        defaultLogSource.append(LogLevel.Debug, strMessage);
    }
    
    public static void error(String strMessage)
    {
        defaultLogSource.append(LogLevel.Error, strMessage);
    }

    public static LogBuffer getDefaultLogSource()
    {
        return defaultLogSource;
    }
}
