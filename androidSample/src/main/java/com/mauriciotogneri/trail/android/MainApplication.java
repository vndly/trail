package com.mauriciotogneri.trail.android;

import android.app.Application;

import com.mauriciotogneri.trail.Trail;

public class MainApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        // display logs in logcat
        Trail.enableLogPrinting(true);

        // enable listeners to receive log events
        Trail.enableListenerNotification(true);
    }
}