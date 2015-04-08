package com.mauriciotogneri.logger;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public final class Logger
{
	private static boolean LOGS_ENABLED = true;
	
	private static List<Listener> listeners = new ArrayList<Listener>();
	
	public enum Level
	{
		VERBOSE, INFO, DEBUG, WARNING, ERROR
	}
	
	public static void setEnabled(boolean enabled)
	{
		Logger.LOGS_ENABLED = enabled;
	}

    public static void addListener(Listener listener)
    {
        Logger.listeners.add(listener);
    }

    public static void removeListener(Listener listener)
    {
        Logger.listeners.remove(listener);
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
			return Logger.class.getName();
		}
	}
	
	public static void log(Level level, String tag, String message, Throwable error)
	{
		if (Logger.LOGS_ENABLED)
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
		
		for (Listener listener : Logger.listeners)
		{
			listener.onLog(level, tag, message, error);
		}
	}
	
	public static void log(Level level, String tag, String message)
	{
		Logger.log(level, tag, message, null);
	}
	
	// ======================= VERBOSE ======================= \\
	
	public static void v(Object tag, Object msg, Throwable e)
	{
		Logger.log(Level.VERBOSE, tag.toString(), msg.toString(), e);
	}
	
	public static void v(Object tag, Object msg)
	{
		Logger.log(Level.VERBOSE, tag.toString(), msg.toString());
	}
	
	public static void v(Object tag, Throwable e)
	{
		Logger.log(Level.VERBOSE, tag.toString(), e.getMessage(), e);
	}
	
	public static void v(Object msg)
	{
		Logger.log(Level.VERBOSE, Logger.getDefaultTag(), msg.toString());
	}
	
	public static void v(Throwable e)
	{
		Logger.log(Level.VERBOSE, Logger.getDefaultTag(), e.getMessage(), e);
	}
	
	// ======================= INFO ======================= \\
	
	public static void i(Object tag, Object msg, Throwable e)
	{
		Logger.log(Level.INFO, tag.toString(), msg.toString(), e);
	}
	
	public static void i(Object tag, Object msg)
	{
		Logger.log(Level.INFO, tag.toString(), msg.toString());
	}
	
	public static void i(Object tag, Throwable e)
	{
		Logger.log(Level.INFO, tag.toString(), e.getMessage(), e);
	}
	
	public static void i(Object msg)
	{
		Logger.log(Level.INFO, Logger.getDefaultTag(), msg.toString());
	}
	
	public static void i(Throwable e)
	{
		Logger.log(Level.INFO, Logger.getDefaultTag(), e.getMessage(), e);
	}
	
	// ======================= DEBUG ======================= \\
	
	public static void d(Object tag, Object msg, Throwable e)
	{
		Logger.log(Level.DEBUG, tag.toString(), msg.toString(), e);
	}
	
	public static void d(Object tag, Object msg)
	{
		Logger.log(Level.DEBUG, tag.toString(), msg.toString());
	}
	
	public static void d(Object tag, Throwable e)
	{
		Logger.log(Level.DEBUG, tag.toString(), e.getMessage(), e);
	}
	
	public static void d(Object msg)
	{
		Logger.log(Level.DEBUG, Logger.getDefaultTag(), msg.toString());
	}
	
	public static void d(Throwable e)
	{
		Logger.log(Level.DEBUG, Logger.getDefaultTag(), e.getMessage(), e);
	}
	
	// ======================= WARNING ======================= \\
	
	public static void w(Object tag, Object msg, Throwable e)
	{
		Logger.log(Level.WARNING, tag.toString(), msg.toString(), e);
	}
	
	public static void w(Object tag, Object msg)
	{
		Logger.log(Level.WARNING, tag.toString(), msg.toString());
	}
	
	public static void w(Object tag, Throwable e)
	{
		Logger.log(Level.WARNING, tag.toString(), e.getMessage(), e);
	}
	
	public static void w(Object msg)
	{
		Logger.log(Level.WARNING, Logger.getDefaultTag(), msg.toString());
	}
	
	public static void w(Throwable e)
	{
		Logger.log(Level.WARNING, Logger.getDefaultTag(), e.getMessage(), e);
	}
	
	// ======================= ERROR ======================= \\
	
	public static void e(Object tag, Object msg, Throwable e)
	{
		Logger.log(Level.ERROR, tag.toString(), msg.toString(), e);
	}
	
	public static void e(Object tag, Object msg)
	{
		Logger.log(Level.ERROR, tag.toString(), msg.toString());
	}
	
	public static void e(Object tag, Throwable e)
	{
		Logger.log(Level.ERROR, tag.toString(), e.getMessage(), e);
	}
	
	public static void e(Object msg)
	{
		Logger.log(Level.ERROR, Logger.getDefaultTag(), msg.toString());
	}
	
	public static void e(Throwable e)
	{
		Logger.log(Level.ERROR, Logger.getDefaultTag(), e.getMessage(), e);
	}
	
	// ======================= LISTENER ======================= \\
	
	public interface Listener
	{
		void onLog(Level level, String tag, String message, Throwable error);
	}
}