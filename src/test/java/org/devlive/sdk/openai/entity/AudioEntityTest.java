package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AudioEntityTest
{
    @Test
    public void testFile()
    {
        /**
         * Checking for empty files returns an exception
         * 校验空文件返回异常
         */
        Assert.assertThrows(ParamException.class, () -> AudioEntity.builder()
                .build());

        /**
         * Checksum file suffix
         * 校验文件后缀
         */
        Assert.assertThrows(ParamException.class, () -> AudioEntity.builder()
                .file(new File(""))
                .build());

        Assert.assertNotNull(AudioEntity.builder()
                .file(new File("a.mp3"))
                .build()
                .getFile());
    }

    @Test
    public void testModel()
    {
        Assert.assertNotNull(AudioEntity.builder()
                .file(new File("a.mp3"))
                .build()
                .getModel());
    }

    @Test
    public void testFormat()
    {
        Assert.assertThrows(ParamException.class, () -> AudioEntity.builder()
                .file(new File("a.mp3"))
                .format("x")
                .build());
    }
}
