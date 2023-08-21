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
public class CompletionEntity
{
    @JsonProperty(value = "prompt")
    private PromptEntity prompt;

    @JsonProperty(value = "temperature")
    @Builder.Default
    private Double temperature = 0.25;

    @JsonProperty(value = "top_k")
    @Builder.Default
    private Integer topK = 40;

    @JsonProperty(value = "top_p")
    @Builder.Default
    private Double topP = 1.0;

    @JsonProperty(value = "candidate_count")
    @Builder.Default
    private Integer candidateCount = 1;

    @JsonProperty(value = "max_output_tokens")
    @Builder.Default
    private Integer maxOutputTokens = 1024;

    @JsonProperty(value = "stop_sequences")
    private List<String> stop;
}
