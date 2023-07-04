package org.devlive.sdk.openai;

import io.reactivex.Single;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.response.ModelResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DefaultApi
{
    /**
     * Lists the currently available models
     */
    @GET("v1/models")
    Single<ModelResponse> fetchModels();

    /**
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     *
     * @param model The ID of the model to use for this request
     */
    @GET("v1/models/{model}")
    Single<ModelEntity> fetchModel(@Path("model") String model);
}
