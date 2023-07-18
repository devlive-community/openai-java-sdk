package org.devlive.sdk.openai.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;

@Slf4j
public class ClaudeInterceptor
        extends DefaultInterceptor
{
    public ClaudeInterceptor()
    {
        log.debug("Claude Interceptor");
    }

    @Override
    protected Request prepared(Request original)
    {
        log.debug("Claude interceptor request url {}", original.url());
        if (StringUtils.isEmpty(this.getApiKey())) {
            throw new ParamException("Invalid Claude token, must be non-empty");
        }
        return original.newBuilder()
                .header("x-api-key", this.getApiKey())
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();
    }
}
