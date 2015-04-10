package com.mauriciotogneri.trail;

import java.util.ArrayList;
import java.util.List;

public final class Trail
{
    private static boolean logsEnabled = true;
    private static boolean listenersEnabled = true;

    private static JavaLogger javaLogger = new JavaLogger();
    private static AndroidLogger androidLogger = new AndroidLogger();

    private static List<Listener> listeners = new ArrayList<Listener>();

    public enum Level
    {
        VERBOSE, INFO, DEBUG, WARNING, ERROR
    }

    public static void enableLogs(boolean enabled)
    {
        Trail.logsEnabled = enabled;
    }

    public static void enableListeners(boolean enabled)
    {
        Trail.listenersEnabled = enabled;
    }

    public static void register(Listener listener)
    {
        Trail.listeners.add(listener);
    }

    public static void unregister(Listener listener)
    {
        Trail.listeners.remove(listener);
    }

    private static String getDefaultTag()
    {
        try
        {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String className = stackTrace[2].getClassName();

            return className.substring(className.lastIndexOf(".") + 1);
        }
        catch (Exception e)
        {
            return Trail.class.getName();
        }
    }

    private static void log(Level level, String tag, String message, Throwable exception)
    {
        if (Trail.logsEnabled)
        {
            if (Trail.androidLogger.isAvailable())
            {
                Trail.androidLogger.log(level, tag, message, exception);
            }
            else
            {
                Trail.javaLogger.log(level, tag, message, exception);
            }
        }

        if (Trail.listenersEnabled)
        {
            for (Listener listener : Trail.listeners)
            {
                listener.onLog(level, tag, message, exception);
            }
        }
    }

    private static void log(Level level, String tag, String message)
    {
        Trail.log(level, tag, message, null);
    }

    // ============================ VERBOSE ============================ \\

    public static void verbose(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.VERBOSE, tag.toString(), message.toString(), exception);
    }

    public static void verbose(Object tag, Object message)
    {
        Trail.log(Level.VERBOSE, tag.toString(), message.toString());
    }

    public static void verbose(Object tag, Throwable exception)
    {
        Trail.log(Level.VERBOSE, tag.toString(), exception.getMessage(), exception);
    }

    public static void verbose(Object message)
    {
        Trail.log(Level.VERBOSE, Trail.getDefaultTag(), message.toString());
    }

    public static void verbose(Throwable exception)
    {
        Trail.log(Level.VERBOSE, Trail.getDefaultTag(), exception.getMessage(), exception);
    }

    // ============================ INFO ============================ \\

    public static void info(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.INFO, tag.toString(), message.toString(), exception);
    }

    public static void info(Object tag, Object message)
    {
        Trail.log(Level.INFO, tag.toString(), message.toString());
    }

    public static void info(Object tag, Throwable exception)
    {
        Trail.log(Level.INFO, tag.toString(), exception.getMessage(), exception);
    }

    public static void info(Object message)
    {
        Trail.log(Level.INFO, Trail.getDefaultTag(), message.toString());
    }

    public static void info(Throwable exception)
    {
        Trail.log(Level.INFO, Trail.getDefaultTag(), exception.getMessage(), exception);
    }

    // ============================ DEBUG ============================ \\

    public static void debug(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.DEBUG, tag.toString(), message.toString(), exception);
    }

    public static void debug(Object tag, Object message)
    {
        Trail.log(Level.DEBUG, tag.toString(), message.toString());
    }

    public static void debug(Object tag, Throwable exception)
    {
        Trail.log(Level.DEBUG, tag.toString(), exception.getMessage(), exception);
    }

    public static void debug(Object message)
    {
        Trail.log(Level.DEBUG, Trail.getDefaultTag(), message.toString());
    }

    public static void debug(Throwable exception)
    {
        Trail.log(Level.DEBUG, Trail.getDefaultTag(), exception.getMessage(), exception);
    }

    // ============================ WARNING ============================ \\

    public static void warning(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.WARNING, tag.toString(), message.toString(), exception);
    }

    public static void warning(Object tag, Object message)
    {
        Trail.log(Level.WARNING, tag.toString(), message.toString());
    }

    public static void warning(Object tag, Throwable exception)
    {
        Trail.log(Level.WARNING, tag.toString(), exception.getMessage(), exception);
    }

    public static void warning(Object message)
    {
        Trail.log(Level.WARNING, Trail.getDefaultTag(), message.toString());
    }

    public static void warning(Throwable exception)
    {
        Trail.log(Level.WARNING, Trail.getDefaultTag(), exception.getMessage(), exception);
    }

    // ============================ ERROR ============================ \\

    public static void error(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.ERROR, tag.toString(), message.toString(), exception);
    }

    public static void error(Object tag, Object message)
    {
        Trail.log(Level.ERROR, tag.toString(), message.toString());
    }

    public static void error(Object tag, Throwable exception)
    {
        Trail.log(Level.ERROR, tag.toString(), exception.getMessage(), exception);
    }

    public static void error(Object message)
    {
        Trail.log(Level.ERROR, Trail.getDefaultTag(), message.toString());
    }

    public static void error(Throwable exception)
    {
        Trail.log(Level.ERROR, Trail.getDefaultTag(), exception.getMessage(), exception);
    }

    // ========================== INTERFACES ========================== \\

    public interface Listener
    {
        void onLog(Level level, String tag, String message, Throwable exception);
    }

    interface LogPrinter
    {
        void log(Level level, String tag, String message, Throwable exception);
    }
}