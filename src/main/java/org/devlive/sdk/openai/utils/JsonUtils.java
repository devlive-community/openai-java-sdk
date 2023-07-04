package org.devlive.sdk.openai.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils<T>
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils()
    {
    }

    public static JsonUtils getInstance()
    {
        return new JsonUtils();
    }

    public T getObject(String json, Class<T> clazz) throws JsonProcessingException
    {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public String getString(T clazz) throws JsonProcessingException
    {
        return OBJECT_MAPPER.writeValueAsString(clazz);
    }
}
