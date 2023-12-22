package org.devlive.sdk.openai;

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
}
