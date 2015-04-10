package com.mauriciotogneri.trail.android;

import android.app.Application;

import com.mauriciotogneri.trail.Trail;

public class MainApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // display log in logcat
        Trail.enableLogs(true);

        // enable listeners to receive log events
        Trail.enableListeners(true);
    }
}