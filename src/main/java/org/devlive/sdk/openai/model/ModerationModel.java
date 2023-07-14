package org.devlive.sdk.openai.model;

public enum ModerationModel
{
    TEXT_MODERATION_STABLE("text-moderation-stable"),
    TEXT_MODERATION_LATEST("text-moderation-latest");

    private final String name;

    public String getName()
    {
        return name;
    }

    ModerationModel(String name)
    {
        this.name = name;
    }
}
