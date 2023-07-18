package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

public class EditEntityTest
{
    @Test
    public void test()
    {
        Assert.assertThrows(ParamException.class, () -> EditEntity.builder()
                .build());
    }
}