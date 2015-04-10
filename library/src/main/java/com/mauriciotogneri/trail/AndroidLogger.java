package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.LogPrinter;

import java.lang.reflect.Method;

public class AndroidLogger implements LogPrinter
{
    private Boolean androidLogLoaded = null;

    private Method methodVerboseShort = null;
    private Method methodVerboseLong = null;

    private Method methodInfoShort = null;
    private Method methodInfoLong = null;

    private Method methodDebugShort = null;
    private Method methodDebugLong = null;

    private Method methodWarningShort = null;
    private Method methodWarningLong = null;

    private Method methodErrorShort = null;
    private Method methodErrorLong = null;

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

    public boolean isAvailable()
    {
        if (this.androidLogLoaded == null)
        {
            checkAndroid();
        }

        return (this.androidLogLoaded != null) && this.androidLogLoaded;
    }

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