package com.mauriciotogneri.logger;

public class Logger
{
	private final GenericLogger defaultLogger;
	private final GenericLogger infoLogger;
	private final GenericLogger debugLogger;
	private final GenericLogger warningLogger;
	private final GenericLogger errorLogger;
	
	private static boolean LOGS_ENABLED = true;
	private static Logger DEFAULT = new Logger(Logger.class.toString());
	
	public enum Level
	{
		INFO, DEBUG, WARNING, ERROR
	}
	
	public Logger(Object tag)
	{
		this(tag, null);
	}
	
	public Logger(Object tag, Level level)
	{
		this.infoLogger = new InfoLogger(tag);
		this.debugLogger = new DebugLogger(tag);
		this.warningLogger = new WarningLogger(tag);
		this.errorLogger = new ErrorLogger(tag);
		this.defaultLogger = getDefaultLogger(level);
	}
	
	private GenericLogger getDefaultLogger(Level level)
	{
		GenericLogger result = null;
		
		if (level == null)
		{
			result = this.infoLogger;
		}
		else
		{
			switch (level)
			{
				case INFO:
					result = this.infoLogger;
					break;
				
				case DEBUG:
					result = this.debugLogger;
					break;
				
				case WARNING:
					result = this.warningLogger;
					break;
				
				case ERROR:
					result = this.errorLogger;
					break;
			}
		}
		
		return result;
	}
	
	public static void setEnable(boolean enabled)
	{
		Logger.LOGS_ENABLED = enabled;
	}
	
	public static boolean isEnabled()
	{
		return Logger.LOGS_ENABLED;
	}
	
	public static Logger getDefault()
	{
		return Logger.DEFAULT;
	}
	
	public static void logException(Object tag, Throwable e)
	{
		Logger.DEFAULT.e(tag, e);
	}
	
	// ======================= INFO ======================= \\
	
	public void i(Object tag, Object msg, Throwable e)
	{
		this.infoLogger.log(tag, msg.toString(), e);
	}
	
	public void i(Object tag, Object msg)
	{
		this.infoLogger.log(tag, msg.toString());
	}
	
	public void i(Object msg)
	{
		this.infoLogger.log(msg.toString());
	}
	
	public void i(Throwable e)
	{
		this.infoLogger.log(e);
	}
	
	// ======================= DEBUG ======================= \\
	
	public void d(Object tag, Object msg, Throwable e)
	{
		this.debugLogger.log(tag, msg.toString(), e);
	}
	
	public void d(Object tag, Object msg)
	{
		this.debugLogger.log(tag, msg.toString());
	}
	
	public void d(Object msg)
	{
		this.debugLogger.log(msg.toString());
	}
	
	public void d(Throwable e)
	{
		this.debugLogger.log(e);
	}
	
	// ======================= WARNING ======================= \\
	
	public void w(Object tag, Object msg, Throwable e)
	{
		this.warningLogger.log(tag, msg.toString(), e);
	}
	
	public void w(Object tag, Object msg)
	{
		this.warningLogger.log(tag, msg.toString());
	}
	
	public void w(Object msg)
	{
		this.warningLogger.log(msg.toString());
	}
	
	public void w(Throwable e)
	{
		this.warningLogger.log(e);
	}
	
	// ======================= ERROR ======================= \\
	
	public void e(Object tag, Object msg, Throwable e)
	{
		this.errorLogger.log(tag, msg.toString(), e);
	}
	
	public void e(Object tag, Object msg)
	{
		this.errorLogger.log(tag, msg.toString());
	}
	
	public void e(Object msg)
	{
		this.errorLogger.log(msg.toString());
	}
	
	public void e(Throwable e)
	{
		this.errorLogger.log(e);
	}
	
	// ======================= DEFAULT LOG ======================= \\
	
	public void l(Object tag, Object msg, Throwable e)
	{
		this.defaultLogger.log(tag, msg.toString(), e);
	}
	
	public void l(Object tag, Object msg)
	{
		this.defaultLogger.log(tag, msg.toString());
	}
	
	public void l(Object msg)
	{
		this.defaultLogger.log(msg.toString());
	}
	
	public void l(Throwable e)
	{
		this.defaultLogger.log(e);
	}
}