package org.devlive.sdk.openai;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.openai.entity.AudioEntity;
import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.EditEntity;
import org.devlive.sdk.openai.entity.EmbeddingEntity;
import org.devlive.sdk.openai.entity.FileEntity;
import org.devlive.sdk.openai.entity.ImageEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.entity.ModerationEntity;
import org.devlive.sdk.openai.entity.UserKeyEntity;
import org.devlive.sdk.openai.model.ProviderModel;
import org.devlive.sdk.openai.model.UrlModel;
import org.devlive.sdk.openai.response.AudioResponse;
import org.devlive.sdk.openai.response.ChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.EditResponse;
import org.devlive.sdk.openai.response.EmbeddingResponse;
import org.devlive.sdk.openai.response.FileResponse;
import org.devlive.sdk.openai.response.ImageResponse;
import org.devlive.sdk.openai.response.ModelResponse;
import org.devlive.sdk.openai.response.ModerationResponse;
import org.devlive.sdk.openai.response.UserKeyResponse;
import org.devlive.sdk.openai.utils.MultipartBodyUtils;
import org.devlive.sdk.openai.utils.ProviderUtils;

@Slf4j
public abstract class DefaultClient
        implements AutoCloseable
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

    public ChatResponse createChatCompletion(ChatEntity configure)
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

    public EmbeddingResponse createEmbeddings(EmbeddingEntity configure)
    {
        return this.api.fetchEmbeddings(ProviderUtils.getUrl(provider, UrlModel.FETCH_EMBEDDINGS),
                        configure)
                .blockingGet();
    }

    public AudioResponse audioTranscriptions(AudioEntity configure)
    {
        MultipartBody.Part fileBody = MultipartBodyUtils.getPart(configure.getFile(), "file");
        return this.api.fetchAudioTranscriptions(ProviderUtils.getUrl(provider, UrlModel.FETCH_AUDIO_TRANSCRIPTIONS),
                        fileBody,
                        configure.convertMap())
                .blockingGet();
    }

    public ModerationResponse moderations(ModerationEntity configure)
    {
        return this.api.fetchModerations(ProviderUtils.getUrl(provider, UrlModel.FETCH_MODERATIONS), configure)
                .blockingGet();
    }

    public EditResponse edit(EditEntity configure)
    {
        return this.api.fetchEdits(ProviderUtils.getUrl(provider, UrlModel.FETCH_EDITS), configure)
                .blockingGet();
    }

    public FileResponse files()
    {
        return this.api.fetchFiles(ProviderUtils.getUrl(provider, UrlModel.FETCH_FILES))
                .blockingGet();
    }

    public FileEntity files(FileEntity configure)
    {
        MultipartBody.Part fileBody = MultipartBodyUtils.getPart(configure.getFile(), "file");
        return this.api.fetchFiles(ProviderUtils.getUrl(provider, UrlModel.FETCH_FILES),
                        fileBody,
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
