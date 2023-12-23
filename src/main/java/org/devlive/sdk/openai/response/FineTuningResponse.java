package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.devlive.sdk.openai.entity.FineTuningEntity;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FineTuningResponse
{
    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "data")
    private List<FineTuningEntity> data;

    @JsonProperty(value = "has_more")
    private String hasMore;
}
