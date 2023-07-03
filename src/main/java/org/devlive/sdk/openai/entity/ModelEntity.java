package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelEntity
{
    @JsonProperty(value = "id")
    private String name;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonProperty(value = "owned_by")
    private String ownedBy;

    @JsonProperty(value = "permission")
    private List<PermissionEntity> permissions;

    @JsonProperty(value = "root")
    private String root;

    @JsonProperty(value = "parent")
    private ModelEntity parent;
}
