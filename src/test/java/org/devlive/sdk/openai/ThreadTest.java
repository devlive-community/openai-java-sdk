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

    @Test
    public void testRetrieveThread()
    {
        String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
        Assert.assertNotNull(client.retrieveThread(threadId));
    }

    @Test
    public void testUpdateThread()
    {
        String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
        ThreadEntity configure = ThreadEntity.builder()
                .build();
        Assert.assertNotNull(client.updateThread(threadId, configure));
    }

    @Test
    public void testDeleteThread()
    {
        String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
        Assert.assertNotNull(client.deleteThread(threadId));
    }
}
