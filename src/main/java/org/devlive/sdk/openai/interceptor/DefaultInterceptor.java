package org.devlive.sdk.openai.interceptor;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.AuthorizedException;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.exception.RequestException;
import org.devlive.sdk.openai.response.DefaultResponse;
import org.devlive.sdk.openai.utils.JsonUtils;

import java.io.IOException;

@Slf4j
public class DefaultInterceptor
        implements Interceptor
{
    @Getter
    @Setter
    private String apiKey;

    public DefaultInterceptor()
    {
        log.warn("Default Interceptor");
    }

    public Request headers(Request original)
    {
        if (StringUtils.isEmpty(this.apiKey)) {
            throw new ParamException("Invalid OpenAi token, must be non-empty");
        }
        return original.newBuilder()
                .header("Authorization", String.format("Bearer %s", this.apiKey))
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        JsonUtils<DefaultResponse> jsonInstance = JsonUtils.getInstance();

        Request original = chain.request();
        Request request = this.headers(original);

        if (ObjectUtils.isNotEmpty(request.body())) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            log.debug("Request body {}", buffer.readUtf8());
        }

        Response response = chain.proceed(request);
        if (!response.isSuccessful()) {
            log.error("Failed to intercept request");
        }

        if (ObjectUtils.isEmpty(response.body())) {
            log.error("Failure to intercept request because no body");
            Preconditions.checkState(false, "Failure to intercept request because no body");
        }

        // Unauthorized
        if (response.code() == 401) {
            ResponseBody body = response.body();
            if (ObjectUtils.isEmpty(body)) {
                throw new NullPointerException("Failed to intercept request because no body");
            }
            DefaultResponse defaultResponse = jsonInstance.getObject(body.string(), DefaultResponse.class);
            log.error("Failure to intercept request because not authorized {}", defaultResponse.getError().getMessage());
            throw new AuthorizedException(defaultResponse.getError().getMessage());
        }

        // Has error
        if (response.code() == 404 || response.code() == 400 || response.code() == 403) {
            ResponseBody body = response.body();
            if (ObjectUtils.isEmpty(body)) {
                throw new NullPointerException("Failed to intercept request because no body");
            }
            DefaultResponse defaultResponse = jsonInstance.getObject(body.string(), DefaultResponse.class);
            log.error("Failure to intercept request because {}", defaultResponse.getError().getMessage());
            throw new RequestException(defaultResponse.getError().getMessage());
        }
        return response;
    }
}
