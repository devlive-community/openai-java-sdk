package org.devlive.sdk.openai.model;

public enum PurposeModel
{
    FINE_TUNE("fine-tune");

    private final String name;

    PurposeModel(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
