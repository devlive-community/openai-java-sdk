package org.devlive.sdk.openai;

import com.google.common.collect.Lists;
import org.devlive.sdk.openai.entity.CompletionChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.CompletionMessageEntity;
import org.devlive.sdk.openai.exception.AuthorizedException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class OpenAiClientTest
{
    private OpenAiClient client;
    private String invalidApiKey = "sk-rh";

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .build();
    }

    @Test
    public void testBuilder()
    {
        Assert.assertNotNull(client);
    }

    @Test
    public void testNoAuthorized()
    {
        client = OpenAiClient.builder()
                .apiKey(invalidApiKey)
                .build();
        Assert.assertThrows(AuthorizedException.class, () -> client.getModels());
    }

    @Test
    public void testGetModels()
    {
        Assert.assertTrue(client.getModels().getModels().size() > 0);
    }

    @Test
    public void testGetModel()
    {
        String model = "text-davinci-003";
        Assert.assertNotNull(client.getModel(model));
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
        List<CompletionMessageEntity> messages = Lists.newArrayList();
        messages.add(CompletionMessageEntity.builder()
                .content("Hello, my name is openai-java-sdk")
                .build());

        CompletionChatEntity configure = CompletionChatEntity.builder()
                .messages(messages)
                .build();

        client.createChatCompletion(configure)
                .getChoices()
                .forEach(choice -> messages.add(choice.getMessage()));

        messages.add(CompletionMessageEntity.builder()
                .content("What is my name?")
                .build());

        Assert.assertTrue(client.createChatCompletion(configure)
                .getChoices()
                .stream()
                .map(v -> v.getMessage().getContent())
                .collect(Collectors.toList())
                .toString()
                .contains("openai-java-sdk"));
    }
}
