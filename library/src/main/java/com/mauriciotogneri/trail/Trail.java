package com.mauriciotogneri.trail;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the Trail library. It manages the global state of the logging system and provides
 * methods to log messages.
 */
public final class Trail
{
    // printing logs enabled/disabled
    private static boolean logsEnabled = true;

    // inform about log events enabled/disabled
    private static boolean listenersEnabled = true;

    // platform specific loggers
    private static JavaLogger javaLogger = new JavaLogger();
    private static AndroidLogger androidLogger = new AndroidLogger();

    // list of registered listeners
    private static List<Listener> listeners = new ArrayList<Listener>();

    /**
     * Available log levels.
     */
    public enum Level
    {
        VERBOSE, DEBUG, INFO, WARNING, ERROR
    }

    /**
     * Enables or disables printing logs.
     *
     * @param enabled true to enable log printing, false to disable
     */
    public static void enableLogs(boolean enabled)
    {
        Trail.logsEnabled = enabled;
    }

    /**
     * Enables or disables informing listeners about log events.
     *
     * @param enabled true to enable log events, false to disable
     */
    public static void enableListeners(boolean enabled)
    {
        Trail.listenersEnabled = enabled;
    }

    /**
     * Registers the listener for log events. WARNING: by calling this method the {@link Trail}
     * class will hold the reference to the listener. This will result in the listener not being
     * garbage collected until it is effectively unregistered.
     *
     * @param listener the listener
     */
    public static void register(Listener listener)
    {
        if (!Trail.listeners.contains(listener))
        {
            Trail.listeners.add(listener);
        }
    }

    /**
     * Unregisters the listener for log events.
     *
     * @param listener the listener
     */
    public static void unregister(Listener listener)
    {
        Trail.listeners.remove(listener);
    }

    /**
     * Returns the code location from where the method was called.
     *
     * @return the code location
     */
    public static CodeLocation getCodeLocation()
    {
        return getCodeLocation(2);
    }

    /**
     * Returns the code location from where the method was called.
     *
     * @param depth the level of depth in the stack to use
     * @return the code location
     */
    private static CodeLocation getCodeLocation(int depth)
    {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StackTraceElement element = stackTrace[depth];
        String className = element.getClassName();

        return new CodeLocation(Thread.currentThread().getName(), className.substring(className.lastIndexOf('.') + 1), element.getMethodName(), element.getLineNumber());
    }

    /**
     * Returns a string representing the default tag. The value of the tag will be the name of the
     * class from there the log was called.
     *
     * @return the default tag
     */
    private static String getDefaultTag()
    {
        try
        {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String className = stackTrace[3].getClassName();

            return className.substring(className.lastIndexOf('.') + 1);
        }
        catch (Exception e)
        {
            return Trail.class.getName();
        }
    }

    /**
     * Prints out the log information using the corresponding {@link LogPrinter}. It also informs
     * the registered listeners about the log event.
     *
     * @param level     the log {@link Level}
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception (can be null)
     */
    private static void log(Level level, String tag, String message, Throwable exception)
    {
        String finalTag = tag;

        if ((finalTag == null) && (Trail.logsEnabled || (Trail.listenersEnabled && !Trail.listeners.isEmpty())))
        {
            finalTag = Trail.getDefaultTag();
        }

        if (Trail.logsEnabled)
        {
            if (Trail.androidLogger.isAvailable())
            {
                Trail.androidLogger.log(level, finalTag, message, exception);
            }
            else
            {
                Trail.javaLogger.log(level, finalTag, message, exception);
            }
        }

        if (Trail.listenersEnabled && !Trail.listeners.isEmpty())
        {
            CodeLocation location = Trail.getCodeLocation(3);

            for (Listener listener : Trail.listeners)
            {
                listener.onLog(level, location, finalTag, message, exception);
            }
        }
    }

    // ============================ VERBOSE ============================ \\

    /**
     * Performs a verbose logging.
     *
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception
     */
    public static void verbose(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.VERBOSE, tag.toString(), message.toString(), exception);
    }

    /**
     * Performs a verbose logging.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void verbose(Object tag, Object message)
    {
        Trail.log(Level.VERBOSE, tag.toString(), message.toString(), null);
    }

    /**
     * Performs a verbose logging.
     *
     * @param tag       the tag
     * @param exception the exception
     */
    public static void verbose(Object tag, Throwable exception)
    {
        Trail.log(Level.VERBOSE, tag.toString(), exception.getMessage(), exception);
    }

    /**
     * Performs a verbose logging.
     *
     * @param message the message
     */
    public static void verbose(Object message)
    {
        Trail.log(Level.VERBOSE, null, message.toString(), null);
    }

    /**
     * Performs a verbose logging.
     *
     * @param exception the exception
     */
    public static void verbose(Throwable exception)
    {
        Trail.log(Level.VERBOSE, null, exception.getMessage(), exception);
    }

    // ============================ DEBUG ============================ \\

    /**
     * Performs a debug logging.
     *
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception
     */
    public static void debug(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.DEBUG, tag.toString(), message.toString(), exception);
    }

    /**
     * Performs a debug logging.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void debug(Object tag, Object message)
    {
        Trail.log(Level.DEBUG, tag.toString(), message.toString(), null);
    }

    /**
     * Performs a debug logging.
     *
     * @param tag       the tag
     * @param exception the exception
     */
    public static void debug(Object tag, Throwable exception)
    {
        Trail.log(Level.DEBUG, tag.toString(), exception.getMessage(), exception);
    }

    /**
     * Performs a debug logging.
     *
     * @param message the message
     */
    public static void debug(Object message)
    {
        Trail.log(Level.DEBUG, null, message.toString(), null);
    }

    /**
     * Performs a debug logging.
     *
     * @param exception the exception
     */
    public static void debug(Throwable exception)
    {
        Trail.log(Level.DEBUG, null, exception.getMessage(), exception);
    }

    // ============================ INFO ============================ \\

    /**
     * Performs an info logging.
     *
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception
     */
    public static void info(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.INFO, tag.toString(), message.toString(), exception);
    }

    /**
     * Performs an info logging.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void info(Object tag, Object message)
    {
        Trail.log(Level.INFO, tag.toString(), message.toString(), null);
    }

    /**
     * Performs an info logging.
     *
     * @param tag       the tag
     * @param exception the exception
     */
    public static void info(Object tag, Throwable exception)
    {
        Trail.log(Level.INFO, tag.toString(), exception.getMessage(), exception);
    }

    /**
     * Performs an info logging.
     *
     * @param message the message
     */
    public static void info(Object message)
    {
        Trail.log(Level.INFO, null, message.toString(), null);
    }

    /**
     * Performs an info logging.
     *
     * @param exception the exception
     */
    public static void info(Throwable exception)
    {
        Trail.log(Level.INFO, null, exception.getMessage(), exception);
    }

    // ============================ WARNING ============================ \\

    /**
     * Performs a warning logging.
     *
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception
     */
    public static void warning(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.WARNING, tag.toString(), message.toString(), exception);
    }

    /**
     * Performs a warning logging.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void warning(Object tag, Object message)
    {
        Trail.log(Level.WARNING, tag.toString(), message.toString(), null);
    }

    /**
     * Performs a warning logging.
     *
     * @param tag       the tag
     * @param exception the exception
     */
    public static void warning(Object tag, Throwable exception)
    {
        Trail.log(Level.WARNING, tag.toString(), exception.getMessage(), exception);
    }

    /**
     * Performs a warning logging.
     *
     * @param message the message
     */
    public static void warning(Object message)
    {
        Trail.log(Level.WARNING, null, message.toString(), null);
    }

    /**
     * Performs a warning logging.
     *
     * @param exception the exception
     */
    public static void warning(Throwable exception)
    {
        Trail.log(Level.WARNING, null, exception.getMessage(), exception);
    }

    // ============================ ERROR ============================ \\

    /**
     * Performs an error logging.
     *
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception
     */
    public static void error(Object tag, Object message, Throwable exception)
    {
        Trail.log(Level.ERROR, tag.toString(), message.toString(), exception);
    }

    /**
     * Performs an error logging.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void error(Object tag, Object message)
    {
        Trail.log(Level.ERROR, tag.toString(), message.toString(), null);
    }

    /**
     * Performs an error logging.
     *
     * @param tag       the tag
     * @param exception the exception
     */
    public static void error(Object tag, Throwable exception)
    {
        Trail.log(Level.ERROR, tag.toString(), exception.getMessage(), exception);
    }

    /**
     * Performs an error logging.
     *
     * @param message the message
     */
    public static void error(Object message)
    {
        Trail.log(Level.ERROR, null, message.toString(), null);
    }

    /**
     * Performs an error logging.
     *
     * @param exception the exception
     */
    public static void error(Throwable exception)
    {
        Trail.log(Level.ERROR, null, exception.getMessage(), exception);
    }

    // ========================== INTERFACES ========================== \\

    /**
     * A listener registers to receive log events in order to process them.
     */
    public interface Listener
    {
        /**
         * Called when a log event occurs.
         *
         * @param level     the log {@link Level}
         * @param location  the location where the log was created
         * @param tag       the tag
         * @param message   the message
         * @param exception the exception (can be null)
         */
        void onLog(Level level, CodeLocation location, String tag, String message, Throwable exception);
    }

    /**
     * A LogPrinter prints the logs in a specific platform (e.g., Java, Android, etc.)
     */
    interface LogPrinter
    {
        /**
         * Prints the log using the correct output.
         *
         * @param level     the log {@link Level}
         * @param tag       the tag
         * @param message   the message
         * @param exception the exception (can be null)
         */
        void log(Level level, String tag, String message, Throwable exception);
    }
}