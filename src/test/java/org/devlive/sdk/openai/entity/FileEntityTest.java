package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileEntityTest
{
    @Test
    public void testFile()
    {
        /**
         * Checking for empty files returns an exception
         * 校验空文件返回异常
         */
        Assert.assertThrows(ParamException.class, () -> FileEntity.builder()
                .build());

        Assert.assertNotNull(FileEntity.builder()
                .file(new File("test.jsonl"))
                .build()
                .getFile());
    }
}
