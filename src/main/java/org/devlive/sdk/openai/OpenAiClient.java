package org.devlive.sdk.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.interceptor.DefaultInterceptor;
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

    private OpenAiClient(OpenAiClientBuilder builder)
    {
        boolean hasApiKey = StringUtils.isNotEmpty(builder.apiKey);
        if (!hasApiKey) {
            log.error("Invalid OpenAi token");
        }
        Preconditions.checkState(hasApiKey, "Invalid OpenAi token");

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
                Preconditions.checkState(apiHost.startsWith("http") || apiHost.startsWith("https"),
                        "Api host must start with http or https");
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
            DefaultInterceptor interceptor = new DefaultInterceptor();
            interceptor.setApiKey(this.apiKey);
            if (ObjectUtils.isEmpty(client)) {
                log.warn("No client, creating default client");
                client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .connectTimeout(this.timeout, this.unit)
                        .writeTimeout(this.timeout, this.unit)
                        .readTimeout(this.timeout, this.unit)
                        .callTimeout(this.timeout, this.unit)
                        .build();
            }

            if (client.interceptors().size() <= 0) {
                throw new ParamException("No interceptors available");
            }

            long count = client.interceptors()
                    .stream()
                    .filter(inter -> inter instanceof DefaultInterceptor)
                    .count();
            if (count <= 0) {
                throw new ParamException("Must inject DefaultInterceptor");
            }
            this.client = client;
            return this;
        }

        public OpenAiClient build()
        {
            return new OpenAiClient(this);
        }
    }
}
