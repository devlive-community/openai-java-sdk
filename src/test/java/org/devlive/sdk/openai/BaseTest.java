package org.devlive.sdk.openai;

import org.junit.Before;

public class BaseTest
{
    protected OpenAiClient client;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .apiKey("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")
                .build();
    }
}
