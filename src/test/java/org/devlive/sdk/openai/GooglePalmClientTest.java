package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.google.CompletionEntity;
import org.devlive.sdk.openai.entity.google.PromptEntity;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.model.ProviderModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GooglePalmClientTest
{
    private OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .provider(ProviderModel.GOOGLE_PALM)
                .model(CompletionModel.TEXT_BISON_001)
                .apiKey(System.getProperty("google.token"))
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
}
