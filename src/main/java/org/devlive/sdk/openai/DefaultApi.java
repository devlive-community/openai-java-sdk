package org.devlive.sdk.openai;

import io.reactivex.Single;
import org.devlive.sdk.openai.entity.CompletionChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.response.CompleteChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.ModelResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DefaultApi
{
    /**
     * Lists the currently available models
     */
    @GET(value = "v1/models")
    Single<ModelResponse> fetchModels();

    /**
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     *
     * @param model The ID of the model to use for this request
     */
    @GET(value = "v1/models/{model}")
    Single<ModelEntity> fetchModel(@Path("model") String model);

    /**
     * Creates a completion for the provided prompt and parameters.
     */
    @POST(value = "v1/completions")
    Single<CompleteResponse> fetchCompletions(@Body CompletionEntity configure);

    /**
     * Creates a model response for the given chat conversation.
     */
    @POST(value = "v1/chat/completions")
    Single<CompleteChatResponse> fetchChatCompletions(@Body CompletionChatEntity configure);
}
