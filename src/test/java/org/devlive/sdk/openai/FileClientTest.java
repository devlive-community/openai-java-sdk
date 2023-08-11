package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.FileEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileClientTest
        extends BaseTest
{
    @Test
    public void testFiles()
    {
        Assert.assertNotNull(this.client.files());

        /**
         * Test upload
         * 测试上传
         */
        String file = this.getClass().getResource("/test.jsonl").getFile();
        Assert.assertNotNull(this.client.files(FileEntity.builder()
                .file(new File(file))
                .build()));
    }
}
