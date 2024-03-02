package org.devlive.sdk.openai.entity;

import com.google.common.collect.Lists;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.ModerationModel;
import org.junit.Assert;
import org.junit.Test;

public class ModerationEntityTest
{
    @Test
    public void testInput()
    {
        Assert.assertThrows(ParamException.class, () -> ModerationEntity.builder()
                .build());

        Assert.assertThrows(ParamException.class, () -> ModerationEntity.builder()
                .inputs(Lists.newArrayList())
                .build());

        Assert.assertTrue(ModerationEntity.builder()
                .inputs(Lists.newArrayList("a"))
                .build()
                .getInputs()
                .size() == 1);
    }

    @Test
    public void testModel()
    {
        Assert.assertNotNull(ModerationEntity.builder()
                .inputs(Lists.newArrayList("a"))
                .model(ModerationModel.TEXT_MODERATION_LATEST)
                .build());
    }
}
