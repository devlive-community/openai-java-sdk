package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.openai.entity.UserKeyEntity;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserKeyResponse
{
    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "data")
    private List<UserKeyEntity> keys;

    @JsonProperty(value = "result")
    private String result;

    @JsonProperty(value = "key")
    private UserKeyEntity key;
}
