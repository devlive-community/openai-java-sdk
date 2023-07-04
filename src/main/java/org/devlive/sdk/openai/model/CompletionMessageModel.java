package org.devlive.sdk.openai.model;

public enum CompletionMessageModel
{
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    FUNCTION("function");

    private final String name;

    public String getName()
    {
        return name;
    }

    CompletionMessageModel(String name)
    {
        this.name = name;
    }
}
