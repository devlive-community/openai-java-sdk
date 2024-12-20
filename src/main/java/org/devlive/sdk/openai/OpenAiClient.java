package org.devlive.sdk.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.common.DefaultApi;
import org.devlive.sdk.common.DefaultClient;
import org.devlive.sdk.common.exception.ParamException;
import org.devlive.sdk.common.interceptor.DefaultInterceptor;
import org.devlive.sdk.openai.interceptor.AzureInterceptor;
import org.devlive.sdk.openai.interceptor.ClaudeInterceptor;
import org.devlive.sdk.openai.interceptor.GooglePaLMInterceptor;
import org.devlive.sdk.openai.interceptor.OpenAiInterceptor;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.model.ProviderModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Slf4j
@Builder
public class OpenAiClient
        extends DefaultClient
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String apiKey;
    private String apiHost;
    private Integer timeout;
    private TimeUnit unit;
    private OkHttpClient client;
    private ProviderModel provider;
    // Azure provider requires
    private String model; // The model name deployed in azure
    private String version;
    // Support see
    private EventSourceListener listener;
    // Support beta
    private String extra;

    private OpenAiClient(OpenAiClientBuilder builder)
    {
        boolean hasApiKey = StringUtils.isNotEmpty(builder.apiKey);
        if (!hasApiKey) {
            log.error("Invalid OpenAi token");
            throw new ParamException("Invalid OpenAi token");
        }

        if (ObjectUtils.isEmpty(builder.provider)) {
            builder.provider(ProviderModel.OPENAI);
        }

        if (builder.provider.equals(ProviderModel.AZURE)) {
            if (ObjectUtils.isEmpty(builder.model)) {
                throw new ParamException("Azure provider model not specified");
            }
            if (ObjectUtils.isEmpty(builder.version)) {
                throw new ParamException("Azure provider version not specified");
            }
        }

        if (ObjectUtils.isEmpty(builder.apiHost)) {
            builder.apiHost(null);
        }
        if (ObjectUtils.isEmpty(builder.timeout)) {
            builder.timeout(null);
        }
        if (ObjectUtils.isEmpty(builder.unit)) {
            builder.unit(null);
        }
        if (ObjectUtils.isEmpty(builder.client)) {
            builder.client(null);
        }
        if (ObjectUtils.isEmpty(builder.listener)) {
            builder.listener(null);
        }

        super.provider = builder.provider;
        super.client = builder.client;
        super.listener = builder.listener;
        super.apiHost = builder.apiHost;
        // Build a remote API client
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.api = new Retrofit.Builder()
                .baseUrl(builder.apiHost)
                .client(builder.client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build()
                .create(DefaultApi.class);
    }

    public static class OpenAiClientBuilder
    {
        public OpenAiClientBuilder apiKey(String apiKey)
        {
            this.apiKey = apiKey;
            return this;
        }

        public OpenAiClientBuilder apiHost(String apiHost)
        {
            if (StringUtils.isEmpty(apiHost)) {
                apiHost = this.getDefaultHost();
            }
            else {
                boolean flag = apiHost.startsWith("http") || apiHost.startsWith("https");
                if (!flag) {
                    throw new ParamException(String.format("Invalid apiHost <%s> must start with http or https", apiHost));
                }
            }
            this.apiHost = apiHost;
            return this;
        }

        public OpenAiClientBuilder timeout(Integer timeout)
        {
            if (ObjectUtils.isEmpty(timeout)) {
                timeout = 30;
            }
            this.timeout = timeout;
            return this;
        }

        public OpenAiClientBuilder unit(TimeUnit unit)
        {
            if (ObjectUtils.isEmpty(unit)) {
                unit = TimeUnit.SECONDS;
            }
            this.unit = unit;
            return this;
        }

        public OpenAiClientBuilder client(OkHttpClient client)
        {
            if (ObjectUtils.isEmpty(this.provider)) {
                this.provider = ProviderModel.OPENAI;
            }

            if (ObjectUtils.isEmpty(client)) {
                log.debug("No client specified, creating default client");
                client = new OkHttpClient.Builder()
                        .connectTimeout(this.timeout, this.unit)
                        .writeTimeout(this.timeout, this.unit)
                        .readTimeout(this.timeout, this.unit)
                        .callTimeout(this.timeout, this.unit)
                        .build();
            }
            // Add default interceptor
            DefaultInterceptor interceptor = new OpenAiInterceptor();
            if (this.provider.equals(ProviderModel.AZURE)) {
                interceptor = new AzureInterceptor();
                interceptor.setVersion(this.version);
                interceptor.setModel(this.model);
            }
            // Anthropic claude interceptor
            if (this.provider.equals(ProviderModel.CLAUDE)) {
                interceptor = new ClaudeInterceptor();
            }
            // Google PaLM
            if (this.provider.equals(ProviderModel.GOOGLE_PALM)) {
                interceptor = new GooglePaLMInterceptor();
                interceptor.setApiKey(this.apiKey);
                interceptor.setModel(this.model);
            }
            interceptor.setApiKey(apiKey);
            interceptor.setExtra(extra);
            client = client.newBuilder()
                    .addInterceptor(interceptor)
                    .build();
            this.client = client;
            return this;
        }

        public OpenAiClientBuilder model(CompletionModel model)
        {
            this.model = model.getName();
            return this;
        }

        public OpenAiClientBuilder model(String model)
        {
            this.model = model;
            return this;
        }

        private String getDefaultHost()
        {
            if (ObjectUtils.isEmpty(this.provider)) {
                this.provider = ProviderModel.OPENAI;
            }
            if (this.provider.equals(ProviderModel.CLAUDE)) {
                return "https://api.anthropic.com";
            }
            if (this.provider.equals(ProviderModel.GOOGLE_PALM)) {
                return "https://generativelanguage.googleapis.com";
            }
            return "https://api.openai.com";
        }

        public OpenAiClient build()
        {
            return new OpenAiClient(this);
        }
    }
}
