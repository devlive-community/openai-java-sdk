package org.devlive.sdk.openai.utils;

import org.devlive.sdk.openai.model.CompletionModel;

import java.util.Arrays;
import java.util.Optional;

public class EnumsUtils
{
    private EnumsUtils()
    {
    }

    public static CompletionModel getCompleteModel(final String name)
    {
        Optional<CompletionModel> optional = Arrays.stream(CompletionModel.values())
                .filter(item -> item.getName().equals(name))
                .findFirst();
        return optional.isPresent() ? optional.get() : null;
    }
}
