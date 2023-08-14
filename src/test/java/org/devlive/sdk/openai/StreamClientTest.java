package org.devlive.sdk.openai;

import lombok.extern.slf4j.Slf4j;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.listener.ConsoleEventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class StreamClientTest
{
    private OpenAiClient client;
    private CountDownLatch countDownLatch;

    @Before
    public void before()
    {
        countDownLatch = new CountDownLatch(1);
        ConsoleEventSourceListener listener = ConsoleEventSourceListener.builder()
                .countDownLatch(countDownLatch)
                .build();
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .listener(listener)
                .build();
    }

    @Test
    public void testCreateCompletion()
    {
        CompletionEntity configure = CompletionEntity.builder()
                .prompt("How to create a stream completion")
                .temperature(2D)
                .build();
        client.createCompletion(configure);
        try {
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            log.error("Interrupted while waiting", e);
        }
    }
}
