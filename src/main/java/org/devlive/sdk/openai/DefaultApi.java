package org.devlive.sdk.openai;

import io.reactivex.Single;
import org.devlive.sdk.openai.response.ModelResponse;
import retrofit2.http.GET;

public interface DefaultApi
{
    /**
     * Get all support model
     *
     * @return All models
     */
    @GET("v1/models")
    Single<ModelResponse> fetchModels();
}
