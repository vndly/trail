package com.mauriciotogneri.logger;

import android.util.Log;

public class Logger
{
	private static boolean LOGS_ENABLED = true;
	
	public static void setEnable(boolean enabled)
	{
		Logger.LOGS_ENABLED = enabled;
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
	
	// ======================= VERBOSE ======================= \\
	
	public static void v(Object tag, Object msg, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.v(tag.toString(), msg.toString(), e);
		}
	}
	
	public static void v(Object tag, Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.v(tag.toString(), msg.toString());
		}
	}
	
	public static void v(Object tag, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.v(tag.toString(), e.getMessage(), e);
		}
	}
	
	public static void v(Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.v(Logger.getDefaultTag(), msg.toString());
		}
	}
	
	public static void v(Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.v(Logger.getDefaultTag(), e.getMessage(), e);
		}
	}
	
	// ======================= INFO ======================= \\
	
	public static void i(Object tag, Object msg, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.i(tag.toString(), msg.toString(), e);
		}
	}
	
	public static void i(Object tag, Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.i(tag.toString(), msg.toString());
		}
	}
	
	public static void i(Object tag, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.i(tag.toString(), e.getMessage(), e);
		}
	}
	
	public static void i(Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.i(Logger.getDefaultTag(), msg.toString());
		}
	}
	
	public static void i(Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.i(Logger.getDefaultTag(), e.getMessage(), e);
		}
	}
	
	// ======================= DEBUG ======================= \\
	
	public static void d(Object tag, Object msg, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.d(tag.toString(), msg.toString(), e);
		}
	}
	
	public static void d(Object tag, Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.d(tag.toString(), msg.toString());
		}
	}
	
	public static void d(Object tag, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.d(tag.toString(), e.getMessage(), e);
		}
	}
	
	public static void d(Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.d(Logger.getDefaultTag(), msg.toString());
		}
	}
	
	public static void d(Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.d(Logger.getDefaultTag(), e.getMessage(), e);
		}
	}
	
	// ======================= WARNING ======================= \\
	
	public static void w(Object tag, Object msg, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.w(tag.toString(), msg.toString(), e);
		}
	}
	
	public static void w(Object tag, Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.w(tag.toString(), msg.toString());
		}
	}
	
	public static void w(Object tag, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.w(tag.toString(), e.getMessage(), e);
		}
	}
	
	public static void w(Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.w(Logger.getDefaultTag(), msg.toString());
		}
	}
	
	public static void w(Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.w(Logger.getDefaultTag(), e.getMessage(), e);
		}
	}
	
	// ======================= ERROR ======================= \\
	
	public static void e(Object tag, Object msg, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.e(tag.toString(), msg.toString(), e);
		}
	}
	
	public static void log(Object tag, Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.e(tag.toString(), msg.toString());
		}
	}
	
	public static void log(Object tag, Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.e(tag.toString(), e.getMessage(), e);
		}
	}
	
	public static void log(Object msg)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.e(Logger.getDefaultTag(), msg.toString());
		}
	}
	
	public static void log(Throwable e)
	{
		if (Logger.LOGS_ENABLED)
		{
			Log.e(Logger.getDefaultTag(), e.getMessage(), e);
		}
	}
}