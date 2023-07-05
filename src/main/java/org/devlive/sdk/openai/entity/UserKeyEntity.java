package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserKeyEntity
{
    @JsonProperty(value = "sensitive_id")
    private String sensitiveId;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonProperty(value = "last_use")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastUseTime;

    @JsonProperty(value = "publishable")
    private boolean publishable;
}
