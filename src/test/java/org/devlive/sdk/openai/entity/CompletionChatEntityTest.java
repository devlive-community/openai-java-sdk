package org.devlive.sdk.openai.entity;


import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Test;

public class CompletionChatEntityTest
{
    @Test
    public void testModel()
    {
        CompletionChatEntity entity = CompletionChatEntity.builder()
                .build();
        Assert.assertTrue(entity.getModel().equals(CompletionModel.GPT_35_TURBO.getName()));

        entity = CompletionChatEntity.builder()
                .model(CompletionModel.GPT_4.getName())
                .build();
        Assert.assertTrue(entity.getModel().equals(CompletionModel.GPT_4.getName()));

        Assert.assertThrows(ParamException.class, () -> CompletionChatEntity.builder()
                .model(CompletionModel.TEXT_ADA_001.getName())
                .build());
    }
}
