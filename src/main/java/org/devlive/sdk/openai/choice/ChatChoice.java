package org.devlive.sdk.openai.choice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.openai.entity.MessageEntity;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatChoice
{
    @JsonProperty(value = "index")
    private String index;

    @JsonProperty(value = "message")
    private MessageEntity message;

    @JsonProperty(value = "finish_reason")
    private String finishReason;
}
