package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.LogPrinter;

import java.io.PrintStream;

/**
 * Specific logger for the Java platform. It uses System.out and System.err to print out messages.
 */
class JavaLogger implements LogPrinter
{
    /**
     * Filters by the log level in order to use System.out or System.err to print out the log.
     *
     * @param level     the log {@link Level}
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception (can be null)
     */
    @Override
    public void log(Level level, String tag, String message, Throwable exception)
    {
        switch (level)
        {
            case VERBOSE:
            case DEBUG:
            case INFO:
            case WARNING:
                log(System.out, tag, message, exception);
                break;

            case ERROR:
                log(System.err, tag, message, exception);
                break;
        }
    }

    /**
     * Prints out the log using the given PrintStream.
     *
     * @param stream    the print stream (System.out or System.err)
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception (can be null)
     */
    private void log(PrintStream stream, String tag, String message, Throwable exception)
    {
        stream.println(tag + ": " + message);

        if (exception != null)
        {
            exception.printStackTrace(stream);
        }

        stream.flush();
    }
}