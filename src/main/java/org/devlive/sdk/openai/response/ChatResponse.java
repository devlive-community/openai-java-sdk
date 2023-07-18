package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.openai.choice.ChatChoice;
import org.devlive.sdk.openai.entity.UsageEntity;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatResponse
{
    @JsonProperty(value = "id")
    private String name;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "choices")
    private List<ChatChoice> choices;

    @JsonProperty(value = "usage")
    private UsageEntity usage;
}
