package org.devlive.sdk.openai.utils;

import okhttp3.HttpUrl;

import java.util.List;

public class HttpUrlUtils
{
    private HttpUrlUtils()
    {}

    /**
     * Removes all path segments from the given HttpUrl.
     *
     * @param httpUrl the HttpUrl from which to remove path segments
     * @return the modified HttpUrl with all path segments removed
     */
    public static HttpUrl removePathSegment(HttpUrl httpUrl)
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
