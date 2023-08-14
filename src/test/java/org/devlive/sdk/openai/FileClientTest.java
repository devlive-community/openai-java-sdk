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
    }

    @Test
    public void testUploadFile()
    {
        String file = this.getClass().getResource("/test.jsonl").getFile();
        Assert.assertNotNull(this.client.uploadFile(FileEntity.builder()
                .file(new File(file))
                .build()));
    }

    @Test
    public void testDeleteFile()
            throws InterruptedException
    {
        String file = this.getClass().getResource("/test.jsonl").getFile();
        FileEntity entity = this.client.uploadFile(FileEntity.builder()
                .file(new File(file))
                .build());
        Thread.sleep(3000);

        Assert.assertTrue(this.client.deleteFile(entity.getId())
                .getDeleted());
    }

    @Test
    public void testRetrieveFile()
            throws InterruptedException
    {
        String file = this.getClass().getResource("/test.jsonl").getFile();
        FileEntity entity = this.client.uploadFile(FileEntity.builder()
                .file(new File(file))
                .build());
        Thread.sleep(3000);

        Assert.assertNotNull(this.client.retrieveFile(entity.getId()));
    }
}
