package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.LogLevel;

/**
 * A LogPrinter prints the logs in a specific platform (e.g., Java, Android, etc.)
 */
interface LogPrinter
{
    /**
     * Prints the log using the correct output.
     *
     * @param level     the log {@link LogLevel}
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception (can be null)
     */
    void log(LogLevel level, String tag, String message, Throwable exception);
}