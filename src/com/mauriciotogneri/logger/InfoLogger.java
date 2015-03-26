package com.mauriciotogneri.logger;

import android.util.Log;

public class InfoLogger extends GenericLogger
{
	public InfoLogger(Object tag)
	{
		super(tag);
	}
	
	@Override
	void l(Object tag, Object msg, Throwable e)
	{
		Log.i(getTag(tag), msg.toString(), e);
	}
	
	@Override
	void l(Object tag, Object msg)
	{
		Log.i(getTag(tag), msg.toString());
	}
	
	@Override
	void l(Object tag, Throwable e)
	{
		Log.i(getTag(tag), e.getMessage(), e);
	}
	
	@Override
	void l(Object msg)
	{
		Log.i(this.tag, msg.toString());
	}
	
	@Override
	void l(Throwable e)
	{
		Log.i(this.tag, e.getMessage(), e);
	}
}