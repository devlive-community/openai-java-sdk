package org.devlive.sdk.openai;

import org.devlive.sdk.openai.response.ModelResponse;

public abstract class DefaultClient
{
    protected DefaultApi api;

    ModelResponse getModels()
    {
        return this.api.fetchModels()
                .blockingGet();
    }
}
