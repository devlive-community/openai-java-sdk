package org.devlive.sdk.openai.exception;

import lombok.Getter;

public class DefaultException
        extends RuntimeException
{
    @Getter
    private final String message;

    public DefaultException(String message)
    {
        this.message = message;
    }
}
