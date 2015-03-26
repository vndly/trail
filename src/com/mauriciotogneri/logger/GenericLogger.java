package com.mauriciotogneri.logger;

public abstract class GenericLogger
{
	protected final String tag;
	
	public GenericLogger(Object tag)
	{
		this.tag = getTag(tag);
	}
	
	final String getTag(Object tag)
	{
		String result = "";
		
		if (tag instanceof String)
		{
			result = tag.toString();
		}
		else if (tag instanceof Class<?>)
		{
			result = ((Class<?>)tag).getName();
		}
		else
		{
			result = tag.getClass().getName();
		}
		
		return result;
	}
	
	public final void log(Object tag, Object msg, Throwable e)
	{
		if (Logger.isEnabled())
		{
			l(tag, msg, e);
		}
	}
	
	public final void log(Object tag, Object msg)
	{
		if (Logger.isEnabled())
		{
			l(tag, msg);
		}
	}
	
	public final void log(Object tag, Throwable e)
	{
		if (Logger.isEnabled())
		{
			l(tag, e);
		}
	}
	
	public final void log(Object msg)
	{
		if (Logger.isEnabled())
		{
			l(msg);
		}
	}
	
	public final void log(Throwable e)
	{
		if (Logger.isEnabled())
		{
			l(e);
		}
	}
	
	abstract void l(Object tag, Object msg, Throwable e);
	
	abstract void l(Object tag, Object msg);
	
	abstract void l(Object tag, Throwable e);
	
	abstract void l(Object msg);
	
	abstract void l(Throwable e);
}