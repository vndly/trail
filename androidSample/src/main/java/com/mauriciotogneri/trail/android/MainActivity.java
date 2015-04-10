package com.mauriciotogneri.trail.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.mauriciotogneri.trail.Trail;
import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.Listener;

public class MainActivity extends Activity implements Listener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Trail.addListener(this);

        Trail.debug("MESSAGE 1");
    }

    @Override
    public void onLog(Level level, String tag, String message, Throwable error)
    {
        TextView labelServer = (TextView) findViewById(R.id.console);
        labelServer.append(level.toString() + ": " + tag + " => " + message);
    }
}