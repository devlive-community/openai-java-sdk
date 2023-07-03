package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.devlive.sdk.openai.entity.ModelEntity;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelResponse
{
    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "data")
    private List<ModelEntity> models;
}
