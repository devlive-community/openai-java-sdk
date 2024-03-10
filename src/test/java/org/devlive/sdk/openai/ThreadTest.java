package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.beta.ThreadEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThreadTest
{
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .extra("assistants=v1")
                .build();
    }

    @Test
    public void testCreateThread()
    {
        ThreadEntity configure = ThreadEntity.builder()
                .build();
        Assert.assertNotNull(client.createThread(configure));
    }
}
