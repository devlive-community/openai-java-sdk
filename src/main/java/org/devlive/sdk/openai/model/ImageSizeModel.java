package org.devlive.sdk.openai.model;

public enum ImageSizeModel
{
    X_256("256x256"),
    X_512("512x512"),
    X_1024("1024x1024");

    private final String name;

    ImageSizeModel(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
