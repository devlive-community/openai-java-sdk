package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompleteModel;
import org.junit.Assert;
import org.junit.Test;

public class CompleteEntityTest
{
    private String prompt = "Enter";

    @Test
    public void testPrompt()
    {
        CompleteEntity entity = CompleteEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertEquals(entity.getPrompt(), prompt);

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .build());
    }

    @Test
    public void testModel()
    {
        CompleteEntity entity = CompleteEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertEquals(entity.getModel(), CompleteModel.TEXT_DAVINCI_003.getName());

        entity = CompleteEntity.builder()
                .prompt(prompt)
                .model(CompleteModel.TEXT_CURIE_001.getName())
                .build();
        Assert.assertEquals(entity.getModel(), CompleteModel.TEXT_CURIE_001.getName());
    }

    @Test
    public void testTemperature()
    {
        CompleteEntity entity = CompleteEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getTemperature() == 1);

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .prompt(prompt)
                .temperature(-1D)
                .build());

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .prompt(prompt)
                .temperature(2.1)
                .build());
    }

    @Test
    public void testMaxTokens()
    {
        CompleteEntity entity = CompleteEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getMaxTokens() == 16);

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .model(CompleteModel.TEXT_DAVINCI_003.getName())
                .prompt(prompt)
                .maxTokens(4097)
                .build());
    }

    @Test
    public void testFrequencyPenalty()
    {
        CompleteEntity entity = CompleteEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getFrequencyPenalty() == 0);

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .prompt(prompt)
                .frequencyPenalty(-2.1)
                .build());

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .prompt(prompt)
                .frequencyPenalty(2.1)
                .build());
    }

    @Test
    public void testPresencePenalty()
    {
        CompleteEntity entity = CompleteEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertTrue(entity.getPresencePenalty() == 0);

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .prompt(prompt)
                .presencePenalty(-2.1)
                .build());

        Assert.assertThrows(ParamException.class, () -> CompleteEntity.builder()
                .prompt(prompt)
                .presencePenalty(2.1)
                .build());
    }
}