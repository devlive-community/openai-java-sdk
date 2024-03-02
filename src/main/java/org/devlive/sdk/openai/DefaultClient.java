package org.devlive.sdk.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.openai.entity.AudioEntity;
import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.EditEntity;
import org.devlive.sdk.openai.entity.EmbeddingEntity;
import org.devlive.sdk.openai.entity.FileEntity;
import org.devlive.sdk.openai.entity.FineTuningEntity;
import org.devlive.sdk.openai.entity.ImageEntity;
import org.devlive.sdk.openai.entity.ModelEntity;
import org.devlive.sdk.openai.entity.ModerationEntity;
import org.devlive.sdk.openai.entity.UserKeyEntity;
import org.devlive.sdk.openai.entity.beta.AssistantsEntity;
import org.devlive.sdk.openai.entity.google.MessageEntity;
import org.devlive.sdk.openai.exception.RequestException;
import org.devlive.sdk.openai.mixin.IgnoreUnknownMixin;
import org.devlive.sdk.openai.model.ProviderModel;
import org.devlive.sdk.openai.model.UrlModel;
import org.devlive.sdk.openai.response.AudioResponse;
import org.devlive.sdk.openai.response.ChatResponse;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.response.EditResponse;
import org.devlive.sdk.openai.response.EmbeddingResponse;
import org.devlive.sdk.openai.response.FileResponse;
import org.devlive.sdk.openai.response.FineTuningResponse;
import org.devlive.sdk.openai.response.ImageResponse;
import org.devlive.sdk.openai.response.ModelResponse;
import org.devlive.sdk.openai.response.ModerationResponse;
import org.devlive.sdk.openai.response.UserKeyResponse;
import org.devlive.sdk.openai.response.beta.AssistantsResponse;
import org.devlive.sdk.openai.utils.MultipartBodyUtils;
import org.devlive.sdk.openai.utils.ProviderUtils;

import java.util.Map;

@Slf4j
public abstract class DefaultClient
        implements AutoCloseable
{
    protected DefaultApi api;
    protected ProviderModel provider;
    protected OkHttpClient client;
    protected String apiHost;
    protected EventSourceListener listener;

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
        String url = ProviderUtils.getUrl(provider, UrlModel.FETCH_COMPLETIONS);
        if (ObjectUtils.isNotEmpty(this.listener)) {
            configure.setStream(true);
            this.createEventSource(url, configure);
            return null;
        }
        else {
            return this.api.fetchCompletions(url, configure)
                    .blockingGet();
        }
    }

    public CompleteResponse createPaLMCompletion(org.devlive.sdk.openai.entity.google.CompletionEntity configure)
    {
        return this.api.fetchPaLMCompletions(ProviderUtils.getUrl(provider, UrlModel.FETCH_COMPLETIONS), configure)
                .blockingGet();
    }

    public CompleteResponse createPaLMChat(org.devlive.sdk.openai.entity.google.ChatEntity configure)
    {
        MessageEntity message = MessageEntity.builder()
                .content("NEXT REQUEST")
                .build();
        configure.getPrompt().getMessages()
                .add(message);
        return this.api.fetchPaLMChat(ProviderUtils.getUrl(provider, UrlModel.FETCH_COMPLETIONS), configure)
                .blockingGet();
    }

    public ChatResponse createChatCompletion(ChatEntity configure)
    {
        String url = ProviderUtils.getUrl(provider, UrlModel.FETCH_CHAT_COMPLETIONS);
        if (ObjectUtils.isNotEmpty(this.listener)) {
            configure.setStream(true);
            this.createEventSource(url, configure);
            return null;
        }
        else {
            return this.api.fetchChatCompletions(url, configure)
                    .blockingGet();
        }
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

    public FileEntity uploadFile(FileEntity configure)
    {
        MultipartBody.Part fileBody = MultipartBodyUtils.getPart(configure.getFile(), "file");
        return this.api.fetchUploadFile(ProviderUtils.getUrl(provider, UrlModel.FETCH_FILES),
                        fileBody,
                        configure.convertMap())
                .blockingGet();
    }

    public FileResponse deleteFile(String id)
    {
        String url = String.join("/", ProviderUtils.getUrl(provider, UrlModel.FETCH_FILES), id);
        return this.api.fetchDeleteFile(url)
                .blockingGet();
    }

    public FileEntity retrieveFile(String id)
    {
        String url = String.join("/", ProviderUtils.getUrl(provider, UrlModel.FETCH_FILES), id);
        return this.api.fetchRetrieveFile(url)
                .blockingGet();
    }

    public Object retrieveFileContent(String id)
    {
        String url = String.join("/", ProviderUtils.getUrl(provider, UrlModel.FETCH_FILES), id, "content");
        return this.api.fetchRetrieveFileContent(url)
                .blockingGet();
    }

    public FineTuningResponse fineTuningJobs()
    {
        return this.api.fetchFineTuningJobs(ProviderUtils.getUrl(provider, UrlModel.FETCH_FINE_TUNING_JOBS))
                .blockingGet();
    }

    public FineTuningResponse createFineTuningJob(FineTuningEntity configure)
    {
        return this.api.fetchCreateFineTuningJob(ProviderUtils.getUrl(provider, UrlModel.FETCH_FINE_TUNING_JOBS), configure)
                .blockingGet();
    }

    public FineTuningResponse fineTuningJobEvents(String jobId)
    {
        String url = String.format(ProviderUtils.getUrl(provider, UrlModel.FETCH_FINE_TUNING_JOBS_EVENTS), jobId);
        return this.api.fetchFineTuningJobEvents(url)
                .blockingGet();
    }

    public FineTuningEntity retrieveFineTuningJob(String jobId)
    {
        String url = String.format(ProviderUtils.getUrl(provider, UrlModel.FETCH_FINE_TUNING_JOBS_CONTENT), jobId);
        return this.api.fetchFineTuningJobContent(url)
                .blockingGet();
    }

    public FineTuningEntity cancelFineTuningJob(String jobId)
    {
        String url = String.format(ProviderUtils.getUrl(provider, UrlModel.FETCH_FINE_TUNING_JOBS_CANCEL), jobId);
        return this.api.fetchCancelFineTuningJob(url)
                .blockingGet();
    }

    public AssistantsEntity createAssistants(AssistantsEntity configure)
    {
        String url = ProviderUtils.getUrl(provider, UrlModel.FETCH_ASSISTANTS);
        return this.api.fetchAssistants(url, configure)
                .blockingGet();
    }

    public AssistantsResponse createAssistantsFile(String fileId,
            String assistantId)
    {
        String url = String.format(ProviderUtils.getUrl(provider, UrlModel.FETCH_ASSISTANTS_FILES), assistantId);
        Map<String, String> configure = Maps.newHashMap();
        configure.put("file_id", fileId);
        return this.api.fetchCreateAssistantFile(url, configure)
                .blockingGet();
    }

    private ObjectMapper createObjectMapper()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(Object.class, IgnoreUnknownMixin.class);
        return objectMapper;
    }

    private void createEventSource(String url, Object configure)
    {
        try {
            EventSource.Factory factory = EventSources.createFactory(this.client);
            ObjectMapper mapper = this.createObjectMapper();
            Request request = new Request.Builder()
                    .url(String.join("/", this.apiHost, url))
                    .post(RequestBody.create(MultipartBodyUtils.JSON, mapper.writeValueAsString(configure)))
                    .build();
            factory.newEventSource(request, this.listener);
        }
        catch (Exception e) {
            throw new RequestException(String.format("Failed to create event source: %s", e.getMessage()));
        }
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
