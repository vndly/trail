package com.mauriciotogneri.trail.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.mauriciotogneri.trail.Trail;
import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.Listener;

import java.io.IOException;

public class AndroidSample extends Activity implements Listener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // register to receive log events
        Trail.register(this);

        // only message
        Trail.verbose("MESSAGE 1");

        // tag & message
        Trail.info("TAG 2", "MESSAGE 2");

        // only exception
        Trail.debug(new NullPointerException("Not pointing anywhere!"));

        // tag & exception
        Trail.warning("TAG 3", new IllegalArgumentException("Wrong argument!"));

        // tag, message & exception
        Trail.error("TAG 4", "MESSAGE 4", new IOException("File not found!"));
    }

    @Override
    public void onLog(Level level, String tag, String message, Throwable exception)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Type:      ").append(level.toString()).append("\n");
        builder.append("Tag:       ").append(tag).append("\n");
        builder.append("Message:   ").append(message).append("\n");

        if (exception != null)
        {
            builder.append("Exception: ").append(exception.getClass().getSimpleName()).append("\n");
        }

        builder.append("\n");

        TextView console = (TextView) findViewById(R.id.console);
        console.append(builder.toString());
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // unregister to stop receiving log events
        Trail.unregister(this);
    }
}