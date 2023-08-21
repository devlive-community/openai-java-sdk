package org.devlive.sdk.openai.interceptor;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.utils.HttpUrlUtils;

import java.util.List;

@Slf4j
public class GooglePaLMInterceptor
        extends DefaultInterceptor
{
    public GooglePaLMInterceptor()
    {
        log.debug("Google PaLM Interceptor");
    }

    @Override
    protected Request prepared(Request original)
    {
        if (StringUtils.isEmpty(this.getApiKey())) {
            throw new ParamException("Invalid Google PaLM token, must be non-empty");
        }
        HttpUrl httpUrl = original.url();
        List<String> pathSegments = Lists.newArrayList();
        httpUrl = HttpUrlUtils.removePathSegment(httpUrl);
        // https://generativelanguage.googleapis.com/v1beta2/models/text-bison-001:generateText?key=YOUR_KEY
        pathSegments.add(0, String.join(":", this.getModel(), "generateText"));
        pathSegments.add(0, "models");
        pathSegments.add(0, "v1beta2");
        httpUrl = httpUrl.newBuilder()
                .host(httpUrl.host())
                .port(httpUrl.port())
                .addPathSegments(String.join("/", pathSegments))
                .addQueryParameter("key", this.getApiKey())
                .build();
        log.debug("Google PaLM interceptor request url {}", httpUrl);
        return original.newBuilder()
                .header("Content-Type", "application/json")
                .url(httpUrl)
                .method(original.method(), original.body())
                .build();
    }
}
