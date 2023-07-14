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

        AZURE_PROVIDER.put(UrlModel.FETCH_COMPLETIONS, "completions");
        AZURE_PROVIDER.put(UrlModel.FETCH_CHAT_COMPLETIONS, "chat/completions");
    }

    private ProviderUtils()
    {
    }

    /**
     * Obtain the service address according to the service provider
     *
     * @param provider Internet service provider
     * @param model    Access address model
     * @return Request address
     */
    public static String getUrl(ProviderModel provider, UrlModel model)
    {
        provider = Optional.ofNullable(provider).orElse(ProviderModel.openai);
        Map<UrlModel, String> selectedProvider = provider.equals(ProviderModel.azure) ? AZURE_PROVIDER : DEFAULT_PROVIDER;

        ProviderModel finalProvider = provider;
        return Optional.ofNullable(selectedProvider.get(model))
                .orElseThrow(() -> new RequestException(String.format("Provider %s not supported %s", finalProvider, model)));
    }
}
