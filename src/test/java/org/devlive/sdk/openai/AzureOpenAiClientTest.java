package org.devlive.sdk.openai;

import com.google.common.collect.Lists;
import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.MessageEntity;
import org.devlive.sdk.openai.exception.RequestException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.model.ProviderModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class AzureOpenAiClientTest
{
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiHost("https://eus-chatgpt.openai.azure.com")
                .apiKey(System.getProperty("azure.token"))
                .provider(ProviderModel.azure)
                .model("text-davinci-002")
                .version("2022-12-01")
                .build();
    }

    @Test
    public void testGetModels()
    {
        Assert.assertThrows(RequestException.class, () -> client.getModels());
    }

    @Test
    public void testCreateCompletion()
    {
        CompletionEntity configure = CompletionEntity.builder()
                .model(CompletionModel.TEXT_DAVINCI_003.getName())
                .prompt("How to create a completion")
                .temperature(2D)
                .build();
        Assert.assertTrue(client.createCompletion(configure).getChoices().size() > 0);
    }

    @Test
    public void testCreateChatCompletion()
    {
        client = OpenAiClient.builder()
                .apiHost("https://eus-chatgpt.openai.azure.com")
                .apiKey(System.getProperty("azure.token"))
                .provider(ProviderModel.azure)
                .model("gpt-35-turbo-0613")
                .version("2023-03-15-preview")
                .build();

        List<MessageEntity> messages = Lists.newArrayList();
        messages.add(MessageEntity.builder()
                .content("Hello, my name is openai-java-sdk")
                .build());

        ChatEntity configure = ChatEntity.builder()
                .messages(messages)
                .build();

        client.createChatCompletion(configure)
                .getChoices()
                .forEach(choice -> messages.add(choice.getMessage()));

        messages.add(MessageEntity.builder()
                .content("What is my name?")
                .build());

        Assert.assertTrue(client.createChatCompletion(configure)
                .getChoices()
                .stream()
                .map(v -> v.getMessage().getContent())
                .collect(Collectors.toList())
                .size() > 0);
    }
}
