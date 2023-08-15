package org.devlive.sdk.openai;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.MessageEntity;
import org.devlive.sdk.openai.listener.ConsoleEventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
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

    @Test
    public void testCreateChatCompletion()
    {
        List<MessageEntity> messages = Lists.newArrayList();
        messages.add(MessageEntity.builder()
                .content("Hello, my name is openai-java-sdk")
                .build());

        ChatEntity configure = ChatEntity.builder()
                .messages(messages)
                .build();

        client.createChatCompletion(configure);
        try {
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            log.error("Interrupted while waiting", e);
        }
    }
}
