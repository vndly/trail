package com.mauriciotogneri.logger;

import android.util.Log;

public class DebugLogger extends GenericLogger
{
	public DebugLogger(Object tag)
	{
		super(tag);
	}
	
	@Override
	void l(Object tag, Object msg, Throwable e)
	{
		Log.d(getTag(tag), msg.toString(), e);
	}
	
	@Override
	void l(Object tag, Object msg)
	{
		Log.d(getTag(tag), msg.toString());
	}
	
	@Override
	void l(Object tag, Throwable e)
	{
		Log.d(getTag(tag), e.getMessage(), e);
	}
	
	@Override
	void l(Object msg)
	{
		Log.d(this.tag, msg.toString());
	}
	
	@Override
	void l(Throwable e)
	{
		Log.d(this.tag, e.getMessage(), e);
	}
}