package org.devlive.sdk.openai;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.io.File;
import java.util.Map;

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
        return this.api.fetchImagesGenerations(ProviderUtils.getUrl(provider, UrlModel.FETCH_IMAGES_GENERATIONS), configure)
                .blockingGet();
    }

    public ImageResponse editImages(File image, File mask, ImageEntity configure)
    {
        MultipartBody.Part imageBody = MultipartBodyUtils.getPart(image, "image");
        MultipartBody.Part maskBody = null;
        if (ObjectUtils.isNotEmpty(mask)) {
            maskBody = MultipartBodyUtils.getPart(mask, "mask");
        }

        Map<String, RequestBody> map = Maps.newConcurrentMap();
        map.put("prompt", RequestBody.create(MultipartBodyUtils.TYPE, configure.getPrompt()));
        map.put("n", RequestBody.create(MultipartBodyUtils.TYPE, configure.getCount().toString()));
        map.put("size", RequestBody.create(MultipartBodyUtils.TYPE, configure.getSize()));
        map.put("response_format", RequestBody.create(MultipartBodyUtils.TYPE, configure.getFormat()));
        if (StringUtils.isNotEmpty(configure.getUser())) {
            map.put("user", RequestBody.create(MultipartBodyUtils.TYPE, configure.getUser()));
        }

        return this.api.fetchImagesEdits(ProviderUtils.getUrl(provider, UrlModel.FETCH_EDITS_GENERATIONS),
                        imageBody,
                        maskBody,
                        map)
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
