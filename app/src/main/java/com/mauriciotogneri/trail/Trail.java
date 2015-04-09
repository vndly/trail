package com.mauriciotogneri.trail;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class Trail
{
    private static boolean LOGS_ENABLED = true;

    private static List<Listener> listeners = new ArrayList<Listener>();

    public enum Level
    {
        VERBOSE, INFO, DEBUG, WARNING, ERROR
    }

    public static void setEnabled(boolean enabled)
    {
        Trail.LOGS_ENABLED = enabled;
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

    public static void log(Level level, String tag, String message, Throwable error)
    {
        if (Trail.LOGS_ENABLED)
        {
            switch (level)
            {
                case VERBOSE:
                    if (error != null)
                    {
                        Log.v(tag, message, error);
                    }
                    else
                    {
                        Log.v(tag, message);
                    }
                    break;

                case INFO:
                    if (error != null)
                    {
                        Log.i(tag, message, error);
                    }
                    else
                    {
                        Log.i(tag, message);
                    }
                    break;

                case DEBUG:
                    if (error != null)
                    {
                        Log.d(tag, message, error);
                    }
                    else
                    {
                        Log.d(tag, message);
                    }
                    break;

                case WARNING:
                    if (error != null)
                    {
                        Log.w(tag, message, error);
                    }
                    else
                    {
                        Log.w(tag, message);
                    }
                    break;

                case ERROR:
                    if (error != null)
                    {
                        Log.e(tag, message, error);
                    }
                    else
                    {
                        Log.e(tag, message);
                    }
                    break;
            }
        }

        for (Listener listener : Trail.listeners)
        {
            listener.onLog(level, tag, message, error);
        }
    }

    public static void log(Level level, String tag, String message)
    {
        Trail.log(level, tag, message, null);
    }

    // ======================= VERBOSE ======================= \\

    public static void v(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.VERBOSE, tag.toString(), msg.toString(), e);
    }

    public static void v(Object tag, Object msg)
    {
        Trail.log(Level.VERBOSE, tag.toString(), msg.toString());
    }

    public static void v(Object tag, Throwable e)
    {
        Trail.log(Level.VERBOSE, tag.toString(), e.getMessage(), e);
    }

    public static void v(Object msg)
    {
        Trail.log(Level.VERBOSE, Trail.getDefaultTag(), msg.toString());
    }

    public static void v(Throwable e)
    {
        Trail.log(Level.VERBOSE, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= INFO ======================= \\

    public static void i(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.INFO, tag.toString(), msg.toString(), e);
    }

    public static void i(Object tag, Object msg)
    {
        Trail.log(Level.INFO, tag.toString(), msg.toString());
    }

    public static void i(Object tag, Throwable e)
    {
        Trail.log(Level.INFO, tag.toString(), e.getMessage(), e);
    }

    public static void i(Object msg)
    {
        Trail.log(Level.INFO, Trail.getDefaultTag(), msg.toString());
    }

    public static void i(Throwable e)
    {
        Trail.log(Level.INFO, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= DEBUG ======================= \\

    public static void d(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.DEBUG, tag.toString(), msg.toString(), e);
    }

    public static void d(Object tag, Object msg)
    {
        Trail.log(Level.DEBUG, tag.toString(), msg.toString());
    }

    public static void d(Object tag, Throwable e)
    {
        Trail.log(Level.DEBUG, tag.toString(), e.getMessage(), e);
    }

    public static void d(Object msg)
    {
        Trail.log(Level.DEBUG, Trail.getDefaultTag(), msg.toString());
    }

    public static void d(Throwable e)
    {
        Trail.log(Level.DEBUG, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= WARNING ======================= \\

    public static void w(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.WARNING, tag.toString(), msg.toString(), e);
    }

    public static void w(Object tag, Object msg)
    {
        Trail.log(Level.WARNING, tag.toString(), msg.toString());
    }

    public static void w(Object tag, Throwable e)
    {
        Trail.log(Level.WARNING, tag.toString(), e.getMessage(), e);
    }

    public static void w(Object msg)
    {
        Trail.log(Level.WARNING, Trail.getDefaultTag(), msg.toString());
    }

    public static void w(Throwable e)
    {
        Trail.log(Level.WARNING, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= ERROR ======================= \\

    public static void e(Object tag, Object msg, Throwable e)
    {
        Trail.log(Level.ERROR, tag.toString(), msg.toString(), e);
    }

    public static void e(Object tag, Object msg)
    {
        Trail.log(Level.ERROR, tag.toString(), msg.toString());
    }

    public static void e(Object tag, Throwable e)
    {
        Trail.log(Level.ERROR, tag.toString(), e.getMessage(), e);
    }

    public static void e(Object msg)
    {
        Trail.log(Level.ERROR, Trail.getDefaultTag(), msg.toString());
    }

    public static void e(Throwable e)
    {
        Trail.log(Level.ERROR, Trail.getDefaultTag(), e.getMessage(), e);
    }

    // ======================= LISTENER ======================= \\

    public interface Listener
    {
        void onLog(Level level, String tag, String message, Throwable error);
    }
}