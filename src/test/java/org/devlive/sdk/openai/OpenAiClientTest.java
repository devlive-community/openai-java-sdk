package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.exception.AuthorizedException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
