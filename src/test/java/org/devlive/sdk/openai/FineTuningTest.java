package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.FineTuningEntity;
import org.devlive.sdk.openai.exception.RequestException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FineTuningTest
{
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .build();
    }

    @Test
    public void testFineTuningJobs()
    {
        Assert.assertNotNull(client.fineTuningJobs());
    }

    @Test
    public void testCreateFineTuningJob()
    {
        FineTuningEntity entity = FineTuningEntity.builder()
                .file("file-Rcxv6RrQXzTuJ2i2LY9kuChL")
                .build();
        Assert.assertThrows(RequestException.class, () -> client.createFineTuningJob(entity));
    }

    @Test
    public void testFineTuningJobEvents()
    {
        Assert.assertThrows(RequestException.class, () -> client.fineTuningJobEvents("ftjob-abc123"));
    }

    @Test
    public void testRetrieveFineTuningJob()
    {
        Assert.assertThrows(RequestException.class, () -> client.retrieveFineTuningJob("ftjob-abc123"));
    }

    @Test
    public void testCancelFineTuningJob()
    {
        Assert.assertThrows(RequestException.class, () -> client.cancelFineTuningJob("ftjob-abc123"));
    }
}
