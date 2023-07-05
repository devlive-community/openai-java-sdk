package org.devlive.sdk.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.interceptor.AzureInterceptor;
import org.devlive.sdk.openai.interceptor.DefaultInterceptor;
import org.devlive.sdk.openai.interceptor.OpenAiInterceptor;
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

    private OpenAiClient(OpenAiClientBuilder builder)
    {
        boolean hasApiKey = StringUtils.isNotEmpty(builder.apiKey);
        if (!hasApiKey) {
            log.error("Invalid OpenAi token");
            throw new ParamException("Invalid OpenAi token");
        }

        if (ObjectUtils.isEmpty(builder.provider)) {
            builder.provider(ProviderModel.openai);
        }

        if (builder.provider.equals(ProviderModel.azure)) {
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

        super.provider = builder.provider;
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
                apiHost = "https://api.openai.com";
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
                this.provider = ProviderModel.openai;
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
            if (this.provider.equals(ProviderModel.azure)) {
                interceptor = new AzureInterceptor();
                interceptor.setVersion(this.version);
                interceptor.setModel(this.model);
            }
            interceptor.setApiKey(apiKey);
            client = client.newBuilder()
                    .addInterceptor(interceptor)
                    .build();
            this.client = client;
            return this;
        }

        public OpenAiClient build()
        {
            return new OpenAiClient(this);
        }
    }
}
