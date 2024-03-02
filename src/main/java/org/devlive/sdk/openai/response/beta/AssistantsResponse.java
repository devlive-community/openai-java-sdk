package org.devlive.sdk.openai.response.beta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantsResponse
{
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdTime;

    @JsonProperty(value = "assistant_id")
    private String assistantId;
}
