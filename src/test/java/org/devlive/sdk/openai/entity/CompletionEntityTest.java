package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Test;

public class CompletionEntityTest
{
    private String prompt = "Enter";

    @Test
    public void testPrompt()
    {
        CompletionEntity entity = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertEquals(entity.getPrompt(), prompt);

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .build());
    }

    @Test
    public void testModel()
    {
        CompletionEntity entity = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertEquals(entity.getModel(), CompletionModel.TEXT_DAVINCI_003.getName());

        entity = CompletionEntity.builder()
                .prompt(prompt)
                .model(CompletionModel.TEXT_CURIE_001)
                .build();
        Assert.assertEquals(entity.getModel(), CompletionModel.TEXT_CURIE_001.getName());
    }

    @Test
    public void testTemperature()
    {
        CompletionEntity entity = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getTemperature() == 1);

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .prompt(prompt)
                .temperature(-1D)
                .build());

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .prompt(prompt)
                .temperature(2.1)
                .build());
    }

    @Test
    public void testMaxTokens()
    {
        CompletionEntity entity = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getMaxTokens() == 16);

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .model(CompletionModel.TEXT_DAVINCI_003)
                .prompt(prompt)
                .maxTokens(4097)
                .build());
    }

    @Test
    public void testFrequencyPenalty()
    {
        CompletionEntity entity = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getFrequencyPenalty() == 0);

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .prompt(prompt)
                .frequencyPenalty(-2.1)
                .build());

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .prompt(prompt)
                .frequencyPenalty(2.1)
                .build());
    }

    @Test
    public void testPresencePenalty()
    {
        CompletionEntity entity = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getPresencePenalty() == 0);

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .prompt(prompt)
                .presencePenalty(-2.1)
                .build());

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .prompt(prompt)
                .presencePenalty(2.1)
                .build());
    }
}