package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChoiceEntity
{
    @JsonProperty(value = "text")
    private String content;

    @JsonProperty(value = "index")
    private long index;

    @JsonProperty(value = "logprobs")
    private String logProb;

    @JsonProperty(value = "finish_reason")
    private String finishReason;
}
