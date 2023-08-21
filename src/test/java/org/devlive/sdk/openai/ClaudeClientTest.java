package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.model.ProviderModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClaudeClientTest
{
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("claude.token"))
                .provider(ProviderModel.CLAUDE)
                .build();
    }

    @Test
    public void testCompletion()
    {
        CompletionEntity configure = CompletionEntity.builder()
                .model(CompletionModel.CLAUDE_2)
                .prompt("How to create a completion")
                .build();
        Assert.assertThrows(RuntimeException.class, () -> client.createCompletion(configure));
    }
}
