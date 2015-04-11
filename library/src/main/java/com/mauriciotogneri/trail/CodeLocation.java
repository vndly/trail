package com.mauriciotogneri.trail;

/**
 * Represents the location of the code that is being executed.
 */
public class CodeLocation
{
    /**
     * The thread name.
     */
    public final String thread;

    /**
     * The class name.
     */
    public final String className;

    /**
     * The method name.
     */
    public final String method;

    /**
     * The line number.
     */
    public final int lineNumber;

    CodeLocation(String thread, String className, String method, int lineNumber)
    {
        this.thread = thread;
        this.className = className;
        this.method = method;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        builder.append(thread);
        builder.append(']');
        builder.append(className);
        builder.append('.');
        builder.append(method);
        builder.append(':');
        builder.append(lineNumber);

        return builder.toString();
    }
}