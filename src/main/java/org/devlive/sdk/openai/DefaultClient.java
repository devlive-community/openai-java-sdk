package org.devlive.sdk.openai;

import org.devlive.sdk.openai.entity.CompletionChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.response.CompleteChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.ModelResponse;

public abstract class DefaultClient
{
    protected DefaultApi api;

    ModelResponse getModels()
    {
        return this.api.fetchModels()
                .blockingGet();
    }

    ModelEntity getModel(String model)
    {
        return this.api.fetchModel(model)
                .blockingGet();
    }

    CompleteResponse createCompletion(CompletionEntity configure)
    {
        return this.api.fetchCompletions(configure)
                .blockingGet();
    }

    CompleteChatResponse createChatCompletion(CompletionChatEntity configure)
    {
        return this.api.fetchChatCompletions(configure)
                .blockingGet();
    }
}
