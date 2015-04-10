package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.LogPrinter;

public class JavaLogger implements LogPrinter
{
    @Override
    public void log(Level level, String tag, String message, Throwable error)
    {
        switch (level)
        {
            case VERBOSE:
            case INFO:
            case DEBUG:
            case WARNING:
                System.out.println(tag + ": " + message);

                if (error != null)
                {
                    error.printStackTrace(System.out);
                }

                System.out.flush();

                break;

            case ERROR:
                System.err.println(tag + ": " + message);

                if (error != null)
                {
                    error.printStackTrace(System.err);
                }

                System.err.flush();

                break;
        }
    }
}