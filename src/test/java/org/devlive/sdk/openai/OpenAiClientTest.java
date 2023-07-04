package org.devlive.sdk.openai;

import org.devlive.sdk.openai.exception.AuthorizedException;
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
}
