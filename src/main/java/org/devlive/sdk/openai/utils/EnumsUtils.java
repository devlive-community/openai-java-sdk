package org.devlive.sdk.openai.utils;

import org.devlive.sdk.openai.model.CompleteModel;

import java.util.Arrays;
import java.util.Optional;

public class EnumsUtils
{
    private EnumsUtils()
    {
    }

    public static CompleteModel getCompleteModel(final String name)
    {
        Optional<CompleteModel> optional = Arrays.stream(CompleteModel.values())
                .filter(item -> item.getName().equals(name))
                .findFirst();
        return optional.isPresent() ? optional.get() : null;
    }
}
