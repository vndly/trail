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

    /**
     * The stack trace.
     */
    public StackTraceElement[] stackTrace;

    CodeLocation(StackTraceElement[] stackTrace)
    {
        this.stackTrace = stackTrace;
        StackTraceElement root = stackTrace[0];

        this.thread = Thread.currentThread().getName();
        String className = root.getClassName();
        this.className = className.substring(className.lastIndexOf('.') + 1);
        this.method = root.getMethodName();
        this.lineNumber = root.getLineNumber();
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