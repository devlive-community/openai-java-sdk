package org.devlive.sdk.openai.model;

public enum EditModel
{
    TEXT_DAVINCI_EDIT_001("text-davinci-edit-001"),
    CODE_DAVINCI_EDIT_001("code-davinci-edit-001");

    private final String name;

    public String getName()
    {
        return name;
    }

    EditModel(String name)
    {
        this.name = name;
    }
}
