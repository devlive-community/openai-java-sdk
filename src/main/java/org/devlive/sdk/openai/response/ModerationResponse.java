package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModerationResponse
{
    @JsonProperty(value = "id")
    private String name;

    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "results")
    private List<CategoryResponse> results;
}
