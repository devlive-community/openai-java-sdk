package org.devlive.sdk.openai;

import io.reactivex.Single;
import org.devlive.sdk.openai.entity.CompletionChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.entity.UserKeyEntity;
import org.devlive.sdk.openai.response.CompleteChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.ModelResponse;
import org.devlive.sdk.openai.response.UserKeyResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface DefaultApi
{
    /**
     * Lists the currently available models
     */
    @GET
    Single<ModelResponse> fetchModels(@Url String url);

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
    @POST
    Single<CompleteResponse> fetchCompletions(@Url String url,
                                              @Body CompletionEntity configure);

    /**
     * Creates a model response for the given chat conversation.
     */
    @POST
    Single<CompleteChatResponse> fetchChatCompletions(@Url String url,
                                                      @Body CompletionChatEntity configure);

    /**
     * Get all keys
     */
    @GET(value = "dashboard/user/api_keys")
    Single<UserKeyResponse> fetchUserAPIKeys();

    /**
     * Create a key for the given
     */
    @POST(value = "dashboard/user/api_keys")
    Single<UserKeyResponse> fetchCreateUserAPIKey(@Body UserKeyEntity configure);
}
