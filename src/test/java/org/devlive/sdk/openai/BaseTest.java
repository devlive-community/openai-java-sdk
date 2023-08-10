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
                .build();
    }
}
