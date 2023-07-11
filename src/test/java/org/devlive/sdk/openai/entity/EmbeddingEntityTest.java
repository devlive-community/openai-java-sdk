package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Test;

public class EmbeddingEntityTest
{
    @Test
    public void testModel()
    {
        Assert.assertThrows(ParamException.class, () -> EmbeddingEntity.builder()
                .model("testModel")
                .build());

        Assert.assertEquals(EmbeddingEntity.builder()
                .model("text-similarity-ada-001")
                .input("Test")
                .build()
                .getModel(), "text-similarity-ada-001");
    }

    @Test
    public void testInput()
    {
        Assert.assertThrows(ParamException.class, () -> EmbeddingEntity.builder()
                .model(CompletionModel.BABBAGE.getName())
                .build());
    }
}