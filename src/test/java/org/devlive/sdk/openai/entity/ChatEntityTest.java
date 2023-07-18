package org.devlive.sdk.openai.entity;


import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Test;

public class ChatEntityTest
{
    @Test
    public void testModel()
    {
        ChatEntity entity = ChatEntity.builder()
                .build();
        Assert.assertTrue(entity.getModel().equals(CompletionModel.GPT_35_TURBO.getName()));

        entity = ChatEntity.builder()
                .model(CompletionModel.GPT_4.getName())
                .build();
        Assert.assertTrue(entity.getModel().equals(CompletionModel.GPT_4.getName()));

        Assert.assertThrows(ParamException.class, () -> ChatEntity.builder()
                .model(CompletionModel.TEXT_ADA_001.getName())
                .build());
    }
}
