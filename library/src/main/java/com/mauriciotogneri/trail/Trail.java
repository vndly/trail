package com.mauriciotogneri.trail;

import java.util.ArrayList;
import java.util.List;

public final class Trail
{
    private static Mode mode = null;
    private static boolean logsEnabled = true;
    private static boolean listenersEnabled = true;

    private static LogPrinter javaLogger = new JavaLogger();
    private static LogPrinter androidLogger = new AndroidLogger();

    private static List<Listener> listeners = new ArrayList<Listener>();

    public enum Level
    {
        VERBOSE, INFO, DEBUG, WARNING, ERROR
    }

    public enum Mode
    {
        JAVA, ANDROID
    }

    public static void setMode(Mode mode)
    {
        Trail.mode = mode;
    }

    public static void enableLogs(boolean enabled)
    {
        Trail.logsEnabled = enabled;
    }

    public static void enableListeners(boolean enabled)
    {
        Trail.listenersEnabled = enabled;
    }

    public static void addListener(Listener listener)
    {
        Trail.listeners.add(listener);
    }

    public static void removeListener(Listener listener)
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

    private static void log(Level level, String tag, String message, Throwable error)
    {
        if (Trail.logsEnabled)
        {
            if (Trail.mode == Mode.JAVA)
            {
                Trail.javaLogger.log(level, tag, message, error);
            }
            else if (Trail.mode == Mode.ANDROID)
            {
                Trail.androidLogger.log(level, tag, message, error);
            }
        }

        if (Trail.listenersEnabled)
        {
            for (Listener listener : Trail.listeners)
            {
                listener.onLog(level, tag, message, error);
            }
        }
    }

    private static void log(Level level, String tag, String message)
    {
        Trail.log(level, tag, message, null);
    }

    // ======================= VERBOSE ======================= \\

    public static void verbose(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.VERBOSE, tag.toString(), msg.toString(), e);
    }

    public static void verbose(Object tag, Object msg)
    {
        Trail.log(Level.VERBOSE, tag.toString(), msg.toString());
    }

    public static void verbose(Object tag, Throwable e)
    {
        Trail.log(Level.VERBOSE, tag.toString(), e.getMessage(), e);
    }

    public static void verbose(Object msg)
    {
        Trail.log(Level.VERBOSE, Trail.getDefaultTag(), msg.toString());
    }

    public static void verbose(Throwable e)
    {
        Trail.log(Level.VERBOSE, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= INFO ======================= \\

    public static void info(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.INFO, tag.toString(), msg.toString(), e);
    }

    public static void info(Object tag, Object msg)
    {
        Trail.log(Level.INFO, tag.toString(), msg.toString());
    }

    public static void info(Object tag, Throwable e)
    {
        Trail.log(Level.INFO, tag.toString(), e.getMessage(), e);
    }

    public static void info(Object msg)
    {
        Trail.log(Level.INFO, Trail.getDefaultTag(), msg.toString());
    }

    public static void info(Throwable e)
    {
        Trail.log(Level.INFO, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= DEBUG ======================= \\

    public static void debug(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.DEBUG, tag.toString(), msg.toString(), e);
    }

    public static void debug(Object tag, Object msg)
    {
        Trail.log(Level.DEBUG, tag.toString(), msg.toString());
    }

    public static void debug(Object tag, Throwable e)
    {
        Trail.log(Level.DEBUG, tag.toString(), e.getMessage(), e);
    }

    public static void debug(Object msg)
    {
        Trail.log(Level.DEBUG, Trail.getDefaultTag(), msg.toString());
    }

    public static void debug(Throwable e)
    {
        Trail.log(Level.DEBUG, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= WARNING ======================= \\

    public static void warning(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.WARNING, tag.toString(), msg.toString(), e);
    }

    public static void warning(Object tag, Object msg)
    {
        Trail.log(Level.WARNING, tag.toString(), msg.toString());
    }

    public static void warning(Object tag, Throwable e)
    {
        Trail.log(Level.WARNING, tag.toString(), e.getMessage(), e);
    }

    public static void warning(Object msg)
    {
        Trail.log(Level.WARNING, Trail.getDefaultTag(), msg.toString());
    }

    public static void warning(Throwable e)
    {
        Trail.log(Level.WARNING, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= ERROR ======================= \\

    public static void error(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.ERROR, tag.toString(), msg.toString(), e);
    }

    public static void error(Object tag, Object msg)
    {
        Trail.log(Level.ERROR, tag.toString(), msg.toString());
    }

    public static void error(Object tag, Throwable e)
    {
        Trail.log(Level.ERROR, tag.toString(), e.getMessage(), e);
    }

    public static void error(Object msg)
    {
        Trail.log(Level.ERROR, Trail.getDefaultTag(), msg.toString());
    }

    public static void error(Throwable e)
    {
        Trail.log(Level.ERROR, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= INTERFACES ======================= \\

    public interface Listener
    {
        void onLog(Level level, String tag, String message, Throwable error);
    }

    interface LogPrinter
    {
        void log(Level level, String tag, String message, Throwable error);
    }
}