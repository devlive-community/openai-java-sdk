package org.devlive.sdk.openai.exception;

public class DefaultException
        extends RuntimeException
{
    private final String message;

    public DefaultException(String message)
    {
        this.message = message;
    }
}
