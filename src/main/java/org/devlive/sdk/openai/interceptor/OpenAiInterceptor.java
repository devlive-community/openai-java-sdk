package org.devlive.sdk.openai.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;

@Slf4j
public class OpenAiInterceptor
        extends DefaultInterceptor
{
    public OpenAiInterceptor()
    {
        log.debug("OpenAi Interceptor");
    }

    public Request prepared(Request original)
    {
        log.debug("OpenAi interceptor request url {}", original.url());
        if (StringUtils.isEmpty(this.getApiKey())) {
            throw new ParamException("Invalid OpenAi token, must be non-empty");
        }

        original = original.newBuilder()
                .header("Authorization", String.format("Bearer %s", this.getApiKey()))
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();

        if (this.getExtra() != null) {
            original = original.newBuilder()
                    .header("OpenAI-Beta", this.getExtra())
                    .build();
        }
        return original;
    }
}
