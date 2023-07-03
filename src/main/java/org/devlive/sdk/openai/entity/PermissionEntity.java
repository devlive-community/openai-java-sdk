package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionEntity
{
    @JsonProperty(value = "id")
    private String name;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonProperty(value = "allow_create_engine")
    private boolean allowCreateEngine;

    @JsonProperty(value = "allow_sampling")
    private boolean allowSampling;

    @JsonProperty(value = "allow_logprobs")
    private boolean allowLogProb;

    @JsonProperty(value = "allow_search_indices")
    private boolean allowSearchIndices;

    @JsonProperty(value = "allow_view")
    private boolean allowView;

    @JsonProperty(value = "allow_fine_tuning")
    private boolean allowFineTuning;

    @JsonProperty(value = "organization")
    private String organization;

    @JsonProperty(value = "group")
    private String group;

    @JsonProperty(value = "is_blocking")
    private boolean isBlocking;
}
