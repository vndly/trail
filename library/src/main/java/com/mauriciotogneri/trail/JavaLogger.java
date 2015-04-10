package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.LogPrinter;

import java.io.PrintStream;

public class JavaLogger implements LogPrinter
{
    @Override
    public void log(Level level, String tag, String message, Throwable exception)
    {
        switch (level)
        {
            case VERBOSE:
            case INFO:
            case DEBUG:
            case WARNING:
                log(System.out, tag, message, exception);
                break;

            case ERROR:
                log(System.err, tag, message, exception);
                break;
        }
    }

    private void log(PrintStream stream, String tag, String message, Throwable exception)
    {
        stream.println(tag + ": " + message);

        if (exception != null)
        {
            exception.printStackTrace(System.out);
        }

        stream.flush();
    }
}