package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryEntity
{
    @JsonProperty(value = "sexual")
    private Object sexual;

    @JsonProperty(value = "hate")
    private Object hate;

    @JsonProperty(value = "harassment")
    private Object harassment;

    @JsonProperty(value = "self-harm")
    private Object selfHarm;

    @JsonProperty(value = "sexual/minors")
    private Object sexualMinors;

    @JsonProperty(value = "hate/threatening")
    private Object hateThreatening;

    @JsonProperty(value = "violence/graphic")
    private Object violenceGraphic;

    @JsonProperty(value = "self-harm/intent")
    private Object selfHarmIntent;

    @JsonProperty(value = "self-harm/instructions")
    private Object selfHarmInstructions;

    @JsonProperty(value = "harassment/threatening")
    private Object harassmentThreatening;

    @JsonProperty(value = "violence")
    private Object violence;
}
