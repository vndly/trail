package com.mauriciotogneri.logger;

import android.util.Log;

public class ErrorLogger extends GenericLogger
{
	public ErrorLogger(Object tag)
	{
		super(tag);
	}
	
	@Override
	void l(Object tag, Object msg, Throwable e)
	{
		Log.e(getTag(tag), msg.toString(), e);
	}
	
	@Override
	void l(Object tag, Object msg)
	{
		Log.e(getTag(tag), msg.toString());
	}
	
	@Override
	void l(Object tag, Throwable e)
	{
		Log.e(getTag(tag), e.getMessage(), e);
	}
	
	@Override
	void l(Object msg)
	{
		Log.e(this.tag, msg.toString());
	}
	
	@Override
	void l(Throwable e)
	{
		Log.e(this.tag, e.getMessage(), e);
	}
}