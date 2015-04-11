package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.LogPrinter;

import java.lang.reflect.Method;

/**
 * Specific logger for the Android platform. It uses the android.util.Log class to print out
 * messages to the LogCat.
 */
class AndroidLogger implements LogPrinter
{
    // null:  Android logging not yet checked
    // true:  Android logging available
    // false: Android logging not available
    private Boolean androidLogLoaded = null;

    // verbose loggers
    private Method methodVerboseShort = null;
    private Method methodVerboseLong = null;

    // debug loggers
    private Method methodDebugShort = null;
    private Method methodDebugLong = null;

    // info loggers
    private Method methodInfoShort = null;
    private Method methodInfoLong = null;

    // warning loggers
    private Method methodWarningShort = null;
    private Method methodWarningLong = null;

    // error loggers
    private Method methodErrorShort = null;
    private Method methodErrorLong = null;

    /**
     * Invokes the corresponding logging method according to the log level.
     *
     * @param level     the log {@link Level}
     * @param tag       the tag
     * @param message   the message
     * @param exception the exception (can be null)
     * @throws RuntimeException if unable to load the Log class from the Android platform
     */
    @Override
    public void log(Level level, String tag, String message, Throwable exception) throws RuntimeException
    {
        if (isAvailable())
        {
            try
            {
                switch (level)
                {
                    case VERBOSE:
                        if (exception != null)
                        {
                            this.methodVerboseLong.invoke(null, tag, message, exception);
                        }
                        else
                        {
                            this.methodVerboseShort.invoke(null, tag, message);
                        }
                        break;

                    case INFO:
                        if (exception != null)
                        {
                            this.methodInfoLong.invoke(null, tag, message, exception);
                        }
                        else
                        {
                            this.methodInfoShort.invoke(null, tag, message);
                        }
                        break;

                    case DEBUG:
                        if (exception != null)
                        {
                            this.methodDebugLong.invoke(null, tag, message, exception);
                        }
                        else
                        {
                            this.methodDebugShort.invoke(null, tag, message);
                        }
                        break;

                    case WARNING:
                        if (exception != null)
                        {
                            this.methodWarningLong.invoke(null, tag, message, exception);
                        }
                        else
                        {
                            this.methodWarningShort.invoke(null, tag, message);
                        }
                        break;

                    case ERROR:
                        if (exception != null)
                        {
                            this.methodErrorLong.invoke(null, tag, message, exception);
                        }
                        else
                        {
                            this.methodErrorShort.invoke(null, tag, message);
                        }
                        break;
                }
            }
            catch (Exception e)
            {
                throw new RuntimeException("Unable to perform logging using android.util.Log");
            }
        }
    }

    /**
     * Checks if the Android platform is available (if not checked yet).
     *
     * @return true if the Android platform is available, false if not available or not checked yet
     */
    public boolean isAvailable()
    {
        if (this.androidLogLoaded == null)
        {
            checkAndroid();
        }

        return (this.androidLogLoaded != null) && this.androidLogLoaded;
    }

    /**
     * Checks if the code is running on an Android platform.
     */
    private void checkAndroid()
    {
        try
        {
            Class<?> logClass = Class.forName("android.util.Log");

            this.methodVerboseShort = logClass.getMethod("v", String.class, String.class);
            this.methodVerboseLong = logClass.getMethod("v", String.class, String.class, Throwable.class);

            this.methodInfoShort = logClass.getMethod("i", String.class, String.class);
            this.methodInfoLong = logClass.getMethod("i", String.class, String.class, Throwable.class);

            this.methodDebugShort = logClass.getMethod("d", String.class, String.class);
            this.methodDebugLong = logClass.getMethod("d", String.class, String.class, Throwable.class);

            this.methodWarningShort = logClass.getMethod("w", String.class, String.class);
            this.methodWarningLong = logClass.getMethod("w", String.class, String.class, Throwable.class);

            this.methodErrorShort = logClass.getMethod("e", String.class, String.class);
            this.methodErrorLong = logClass.getMethod("e", String.class, String.class, Throwable.class);

            this.androidLogLoaded = true;
        }
        catch (Exception e)
        {
            this.androidLogLoaded = false;
        }
    }
}