package org.devlive.sdk.openai.model;

public enum MessageModel
{
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    FUNCTION("function");

    private final String name;

    MessageModel(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
