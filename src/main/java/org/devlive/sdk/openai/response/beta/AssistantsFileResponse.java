package org.devlive.sdk.openai.response.beta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.devlive.sdk.openai.entity.beta.AssistantsFileEntity;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantsFileResponse
{
    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "data")
    private List<AssistantsFileEntity> data;

    @JsonProperty(value = "first_id")
    private String firstId;

    @JsonProperty(value = "last_id")
    private String lastId;

    @JsonProperty(value = "has_more")
    private boolean hasMore;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "deleted")
    private boolean deleted;
}
