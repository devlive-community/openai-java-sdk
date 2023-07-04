package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.devlive.sdk.openai.model.CompleteModel;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompleteEntity
{
    @JsonProperty(value = "model", defaultValue = "TEXT_DAVINCI_003")
    @Builder.Default
    private CompleteModel model = CompleteModel.TEXT_DAVINCI_003;

    @JsonProperty(value = "prompt", required = true)
    private String prompt;

    @JsonProperty(value = "temperature")
    private Double temperature;

    @JsonProperty(value = "max_tokens")
    private Integer maxTokens;

    @JsonProperty(value = "top_p")
    private Double topP;

    @JsonProperty(value = "best_of")
    private Double bestOf;

    @JsonProperty(value = "frequency_penalty")
    private Double frequencyPenalty;

    @JsonProperty(value = "presence_penalty")
    private Double presencePenalty;

    @JsonProperty(value = "stop")
    private List<String> stop;
}
