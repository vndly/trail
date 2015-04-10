package com.mauriciotogneri.trail.java;

import com.mauriciotogneri.trail.Trail;
import com.mauriciotogneri.trail.Trail.Level;
import com.mauriciotogneri.trail.Trail.Listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JavaSample implements Listener
{
    private BufferedWriter bufferedWriter = null;

    public static void main(String[] args) throws IOException
    {
        // display logs in console
        Trail.enableLogs(true);

        // enable listeners to receive log events
        Trail.enableListeners(true);

        JavaSample javaSample = new JavaSample();

        // register the object to receive log events
        Trail.register(javaSample);

        // execute the example
        javaSample.run();

        // unregister the object to stop receiving log events
        Trail.unregister(javaSample);
    }

    private void run() throws IOException
    {
        // creating a temp file and displaying the path
        File tempFile = File.createTempFile("tempfile", ".tmp");
        System.out.println("Temp file: " + tempFile.getAbsolutePath());
        this.bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

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

        bufferedWriter.close();
    }

    @Override
    public void onLog(Level level, String tag, String message, Throwable exception)
    {
        try
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

            bufferedWriter.write(builder.toString());
            bufferedWriter.flush();
        }
        catch (IOException e)
        {
        }
    }
}