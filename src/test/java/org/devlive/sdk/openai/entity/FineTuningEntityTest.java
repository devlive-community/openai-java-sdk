package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

public class FineTuningEntityTest
{
    @Test
    public void testLimit()
    {
        FineTuningEntity entity = FineTuningEntity.builder()
                .limit(null)
                .build();
        Assert.assertTrue(entity.getLimit() == 20);

        Assert.assertThrows(ParamException.class, () -> FineTuningEntity.builder()
                .limit(0)
                .build());

        entity = FineTuningEntity.builder()
                .limit(10)
                .build();
        Assert.assertTrue(entity.getLimit() == 10);
    }

    @Test
    public void testAfter()
    {
        FineTuningEntity entity = FineTuningEntity.builder()
                .after(null)
                .build();
        Assert.assertNull(entity.getAfter());

        entity = FineTuningEntity.builder()
                .after("test")
                .build();
        Assert.assertEquals(entity.getAfter(), "test");
    }
}