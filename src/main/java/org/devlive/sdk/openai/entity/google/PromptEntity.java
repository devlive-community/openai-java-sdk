package org.devlive.sdk.openai.entity.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromptEntity
{
    @JsonProperty(value = "text")
    private String text;

    @JsonProperty(value = "context")
    private String context;

    @JsonProperty(value = "examples")
    private List<ExampleEntity> examples;

    @JsonProperty(value = "messages")
    private List<MessageEntity> messages;
}
