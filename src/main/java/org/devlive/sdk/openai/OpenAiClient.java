package org.devlive.sdk.openai;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

        if (ObjectUtils.isEmpty(apiHost)) {
            builder.apiHost(null);
        }
        if (ObjectUtils.isEmpty(timeout)) {
            builder.timeout(null);
        }
        if (ObjectUtils.isEmpty(unit)) {
            builder.unit(null);
        }
        if (ObjectUtils.isEmpty(client)) {
            builder.client(null);
        }

        // Build a remote API client
        this.api = new Retrofit.Builder()
                .baseUrl(builder.apiHost)
                .client(builder.client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
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
            } else {
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
            if (ObjectUtils.isEmpty(client)) {
                log.warn("No client, creating default client");
                DefaultInterceptor interceptor = new DefaultInterceptor();
                interceptor.setApiKey(this.apiKey);
                client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .connectTimeout(this.timeout, this.unit)
                        .writeTimeout(this.timeout, this.unit)
                        .readTimeout(this.timeout, this.unit)
                        .callTimeout(this.timeout, this.unit)
                        .build();
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
