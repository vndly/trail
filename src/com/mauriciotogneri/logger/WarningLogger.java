package com.mauriciotogneri.logger;

import android.util.Log;

public class WarningLogger extends GenericLogger
{
	public WarningLogger(Object tag)
	{
		super(tag);
	}
	
	@Override
	void l(Object tag, Object msg, Throwable e)
	{
		Log.w(getTag(tag), msg.toString(), e);
	}
	
	@Override
	void l(Object tag, Object msg)
	{
		Log.w(getTag(tag), msg.toString());
	}
	
	@Override
	void l(Object tag, Throwable e)
	{
		Log.w(getTag(tag), e.getMessage(), e);
	}
	
	@Override
	void l(Object msg)
	{
		Log.w(this.tag, msg.toString());
	}
	
	@Override
	void l(Throwable e)
	{
		Log.w(this.tag, e.getMessage(), e);
	}
}