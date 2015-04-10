package com.mauriciotogneri.trail;

import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.LogPrinter;

import java.lang.reflect.Method;

public class AndroidLogger implements LogPrinter
{
    private boolean androidLogLoaded = false;

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
    public void log(Level level, String tag, String message, Throwable error) throws RuntimeException
    {
        if (!this.androidLogLoaded)
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
            }
            catch (Exception e)
            {
                throw new RuntimeException("Unable to load android.util.Log class. Is the code running in an Android environment?");
            }

            this.androidLogLoaded = true;
        }

        try
        {
            switch (level)
            {
                case VERBOSE:
                    if (error != null)
                    {
                        this.methodVerboseLong.invoke(null, tag, message, error);
                    }
                    else
                    {
                        this.methodVerboseShort.invoke(null, tag, message);
                    }
                    break;

                case INFO:
                    if (error != null)
                    {
                        this.methodInfoLong.invoke(null, tag, message, error);
                    }
                    else
                    {
                        this.methodInfoShort.invoke(null, tag, message);
                    }
                    break;

                case DEBUG:
                    if (error != null)
                    {
                        this.methodDebugLong.invoke(null, tag, message, error);
                    }
                    else
                    {
                        this.methodDebugShort.invoke(null, tag, message);
                    }
                    break;

                case WARNING:
                    if (error != null)
                    {
                        this.methodWarningLong.invoke(null, tag, message, error);
                    }
                    else
                    {
                        this.methodWarningShort.invoke(null, tag, message);
                    }
                    break;

                case ERROR:
                    if (error != null)
                    {
                        this.methodErrorLong.invoke(null, tag, message, error);
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