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
public class UsageEntity
{
    @JsonProperty(value = "prompt_tokens")
    public long promptTokens;

    @JsonProperty(value = "completion_tokens")
    public long completionTokens;

    @JsonProperty(value = "total_tokens")
    public long totalTokens;
}
