package org.devlive.sdk.openai;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.openai.entity.CompletionChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.ImageEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.entity.UserKeyEntity;
import org.devlive.sdk.openai.model.ProviderModel;
import org.devlive.sdk.openai.model.UrlModel;
import org.devlive.sdk.openai.response.CompleteChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.ImageResponse;
import org.devlive.sdk.openai.response.ModelResponse;
import org.devlive.sdk.openai.response.UserKeyResponse;
import org.devlive.sdk.openai.utils.MultipartBodyUtils;
import org.devlive.sdk.openai.utils.ProviderUtils;

@Slf4j
public abstract class DefaultClient implements AutoCloseable
{
    protected DefaultApi api;
    protected ProviderModel provider;
    protected OkHttpClient client;

    public ModelResponse getModels()
    {
        return this.api.fetchModels(ProviderUtils.getUrl(provider, UrlModel.FETCH_MODELS))
                .blockingGet();
    }

    public ModelEntity getModel(String model)
    {
        return this.api.fetchModel(model)
                .blockingGet();
    }

    public CompleteResponse createCompletion(CompletionEntity configure)
    {
        return this.api.fetchCompletions(ProviderUtils.getUrl(provider, UrlModel.FETCH_COMPLETIONS), configure)
                .blockingGet();
    }

    public CompleteChatResponse createChatCompletion(CompletionChatEntity configure)
    {
        return this.api.fetchChatCompletions(ProviderUtils.getUrl(provider, UrlModel.FETCH_CHAT_COMPLETIONS), configure)
                .blockingGet();
    }

    public UserKeyResponse getKeys()
    {
        return this.api.fetchUserAPIKeys()
                .blockingGet();
    }

    public UserKeyResponse createUserAPIKey(UserKeyEntity configure)
    {
        return this.api.fetchCreateUserAPIKey(configure)
                .blockingGet();
    }

    public ImageResponse createImages(ImageEntity configure)
    {
        configure.setIsVariation(null);
        configure.setIsEdit(null);
        return this.api.fetchImagesGenerations(ProviderUtils.getUrl(provider, UrlModel.FETCH_IMAGES_GENERATIONS), configure)
                .blockingGet();
    }

    public ImageResponse editImages(ImageEntity configure)
    {
        MultipartBody.Part imageBody = MultipartBodyUtils.getPart(configure.getImage(), "image");
        MultipartBody.Part maskBody = null;
        if (ObjectUtils.isNotEmpty(configure.getMask())) {
            maskBody = MultipartBodyUtils.getPart(configure.getMask(), "mask");
        }
        return this.api.fetchImagesEdits(ProviderUtils.getUrl(provider, UrlModel.FETCH_IMAGES_EDITS),
                        imageBody,
                        maskBody,
                        configure.convertMap())
                .blockingGet();
    }

    public ImageResponse variationsImages(ImageEntity configure)
    {
        MultipartBody.Part imageBody = MultipartBodyUtils.getPart(configure.getImage(), "image");
        return this.api.fetchImagesVariations(ProviderUtils.getUrl(provider, UrlModel.FETCH_IMAGES_VARIATIONS),
                        imageBody,
                        configure.convertMap())
                .blockingGet();
    }

    public void close()
    {
        if (ObjectUtils.isNotEmpty(this.client)) {
            this.client.dispatcher().cancelAll();
            this.client.connectionPool().evictAll();
            this.client = null;
        }
    }
}
