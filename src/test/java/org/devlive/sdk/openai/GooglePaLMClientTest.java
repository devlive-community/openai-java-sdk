package org.devlive.sdk.openai;

import com.google.common.collect.Lists;
import org.devlive.sdk.openai.entity.google.ChatEntity;
import org.devlive.sdk.openai.entity.google.CompletionEntity;
import org.devlive.sdk.openai.entity.google.MessageEntity;
import org.devlive.sdk.openai.entity.google.PromptEntity;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.model.ProviderModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GooglePaLMClientTest
{
    private String googleToken = System.getProperty("google.token");
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .provider(ProviderModel.GOOGLE_PALM)
                .model(CompletionModel.TEXT_BISON_001)
                .apiKey(googleToken)
                .build();
    }

    @Test
    public void testCompletion()
    {
        PromptEntity prompt = PromptEntity.builder()
                .text("How to create a completion")
                .build();
        CompletionEntity configure = CompletionEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertNotNull(client.createPaLMCompletion(configure).getCandidates());
    }

    @Test
    public void testChat()
    {
        client = OpenAiClient.builder()
                .provider(ProviderModel.GOOGLE_PALM)
                .model(CompletionModel.CHAT_BISON_001)
                .apiKey(googleToken)
                .build();

        List<MessageEntity> messages = Lists.newArrayList();
        messages.add(MessageEntity.builder()
                .content("Hello, my name is openai-java-sdk")
                .build());

        PromptEntity prompt = PromptEntity.builder()
                .messages(messages)
                .build();

        ChatEntity configure = ChatEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertNotNull(client.createPaLMChat(configure).getCandidates());
    }
}
