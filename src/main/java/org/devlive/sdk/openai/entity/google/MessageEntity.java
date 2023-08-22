package org.devlive.sdk.openai.entity.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageEntity
{
    @JsonProperty(value = "content")
    private String content;
}
