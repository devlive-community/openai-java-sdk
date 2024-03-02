package org.devlive.sdk.openai.entity.beta;

import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Test;

public class AssistantsEntityTest
{
    @Test
    public void testModel()
    {
        Assert.assertThrows(ParamException.class, () -> AssistantsEntity.builder()
                .model(null)
                .build());

        Assert.assertNotNull(AssistantsEntity.builder()
                .model(CompletionModel.GPT_4)
                .build());
    }

    @Test
    public void testName()
    {
        Assert.assertThrows(ParamException.class, () -> AssistantsEntity.builder()
                .model(CompletionModel.GPT_4)
                .name("Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long." +
                        "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long." +
                        "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.")
                .build());
    }
}