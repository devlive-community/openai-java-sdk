package org.devlive.sdk.openai.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;

import java.util.List;

@Slf4j
public class AzureInterceptor
        extends DefaultInterceptor
{
    public AzureInterceptor()
    {
        log.debug("Azure Interceptor");
    }

    @Override
    protected Request prepared(Request original)
    {
        HttpUrl httpUrl = original.url();
        List<String> pathSegments = httpUrl.pathSegments();
        // Remove all path segments
        httpUrl = this.removePathSegment(httpUrl);
        // https://${your-resource-name}.openai.azure.com/openai/deployments/${deployment-id}/
        pathSegments.add(0, this.getModel());
        pathSegments.add(0, "deployments");
        pathSegments.add(0, "openai");
        httpUrl = httpUrl.newBuilder()
                .host(httpUrl.host())
                .port(httpUrl.port())
                .addPathSegments(String.join("/", pathSegments))
                .addQueryParameter("api-version", this.getVersion())
                .build();
        log.debug("Azure interceptor request url {}", httpUrl.toString());
        if (StringUtils.isEmpty(this.getApiKey())) {
            throw new ParamException("Invalid OpenAi token, must be non-empty");
        }
        return original.newBuilder()
                .header("api-key", this.getApiKey())
                .header("Content-Type", "application/json")
                .url(httpUrl)
                .method(original.method(), original.body())
                .build();
    }

    private HttpUrl removePathSegment(HttpUrl httpUrl)
    {
        List<String> pathSegments = httpUrl.pathSegments();
        for (int i = 0; i < pathSegments.size(); i++) {
            httpUrl = httpUrl.newBuilder()
                    .removePathSegment(0)
                    .build();
        }
        return httpUrl;
    }
}
