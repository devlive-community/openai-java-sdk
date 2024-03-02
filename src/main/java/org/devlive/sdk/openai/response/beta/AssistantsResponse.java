package org.devlive.sdk.openai.response.beta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.devlive.sdk.openai.entity.beta.AssistantsEntity;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantsResponse
{
    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "data")
    private List<AssistantsEntity> data;

    @JsonProperty(value = "first_id")
    private String firstId;

    @JsonProperty(value = "last_id")
    private String lastId;

    @JsonProperty(value = "has_more")
    private boolean hasMore;
}
