package com.mauriciotogneri.trail.java;

import com.mauriciotogneri.trail.Trail;
import com.mauriciotogneri.trail.Trail.Listener;
import com.mauriciotogneri.trail.TrailLog;

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
        Trail.verbose("MESSAGE 1 | " + Trail.getCodeLocation());

        // only exception
        Trail.debug(new NullPointerException("Not pointing anywhere!"));

        // tag & message
        Trail.info("TAG 2", "MESSAGE 2 | " + Trail.getCodeLocation());

        // tag & exception
        Trail.warning("TAG 3", new IllegalArgumentException("Wrong argument!"));

        // tag, message & exception
        Trail.error("TAG 4", "MESSAGE 4", new IOException("File not found!"));

        bufferedWriter.close();
    }

    @Override
    public void onLog(TrailLog log)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            builder.append("Type:      ").append(log.level.toString()).append("\n");
            builder.append("Tag:       ").append(log.tag).append("\n");
            builder.append("Message:   ").append(log.message).append("\n");
            builder.append("Location:  ").append(log.location).append("\n");

            if (log.hasException())
            {
                builder.append("Exception: ").append(log.exception.getClass().getSimpleName()).append("\n");
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