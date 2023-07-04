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
public class ChoiceChatEntity
{
    @JsonProperty(value = "index")
    private String index;

    @JsonProperty(value = "message")
    private CompletionMessageEntity message;

    @JsonProperty(value = "finish_reason")
    private String finishReason;
}
