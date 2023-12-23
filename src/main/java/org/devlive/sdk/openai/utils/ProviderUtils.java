package org.devlive.sdk.openai.utils;

import org.devlive.sdk.openai.exception.RequestException;
import org.devlive.sdk.openai.model.ProviderModel;
import org.devlive.sdk.openai.model.UrlModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProviderUtils
{
    private static final Map<UrlModel, String> DEFAULT_PROVIDER = new HashMap<>();
    private static final Map<UrlModel, String> AZURE_PROVIDER = new HashMap<>();
    private static final Map<UrlModel, String> CLAUDE_PROVIDER = new HashMap<>();

    static {
        DEFAULT_PROVIDER.put(UrlModel.FETCH_MODELS, "v1/models");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_MODEL, "v1/models/{model}");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_COMPLETIONS, "v1/completions");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_CHAT_COMPLETIONS, "v1/chat/completions");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_IMAGES_GENERATIONS, "v1/images/generations");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_IMAGES_EDITS, "v1/images/edits");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_IMAGES_VARIATIONS, "v1/images/variations");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_EMBEDDINGS, "v1/embeddings");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_AUDIO_TRANSCRIPTIONS, "v1/audio/transcriptions");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_MODERATIONS, "v1/moderations");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_EDITS, "v1/edits");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_FILES, "v1/files");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_FINE_TUNING_JOBS, "v1/fine_tuning/jobs");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_FINE_TUNING_JOBS_EVENTS, "v1/fine_tuning/jobs/%s/events");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_FINE_TUNING_JOBS_CONTENT, "v1/fine_tuning/jobs/%s");
        DEFAULT_PROVIDER.put(UrlModel.FETCH_FINE_TUNING_JOBS_CANCEL, "v1/fine_tuning/jobs/%s/cancel");

        AZURE_PROVIDER.put(UrlModel.FETCH_COMPLETIONS, "completions");
        AZURE_PROVIDER.put(UrlModel.FETCH_CHAT_COMPLETIONS, "chat/completions");

        CLAUDE_PROVIDER.put(UrlModel.FETCH_COMPLETIONS, "v1/complete");
    }

    private ProviderUtils()
    {
    }

    /**
     * Obtain the service address according to the service provider
     *
     * @param provider Internet service provider
     * @param model Access address model
     * @return Request address
     */
    public static String getUrl(ProviderModel provider, UrlModel model)
    {
        provider = Optional.ofNullable(provider).orElse(ProviderModel.OPENAI);
        Map<UrlModel, String> selectedProvider = DEFAULT_PROVIDER;
        if (provider.equals(ProviderModel.AZURE)) {
            selectedProvider = AZURE_PROVIDER;
        }
        if (provider.equals(ProviderModel.CLAUDE)) {
            selectedProvider = CLAUDE_PROVIDER;
        }
        ProviderModel finalProvider = provider;
        return Optional.ofNullable(selectedProvider.get(model))
                .orElseThrow(() -> new RequestException(String.format("Provider %s not supported %s", finalProvider, model)));
    }
}
