package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.MessageModel;
import org.devlive.sdk.openai.utils.EnumsUtils;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageEntity
{
    @JsonProperty(value = "role")
    private String role;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "name")
    private String name;

    private MessageEntity(MessageEntityBuilder builder)
    {
        if (StringUtils.isEmpty(builder.role)) {
            builder.role(MessageModel.USER.getName());
        }
        this.role = builder.role;

        if (StringUtils.isEmpty(builder.content)) {
            builder.content(null);
        }
        this.content = builder.content;

        if (StringUtils.isEmpty(builder.name)) {
            builder.name("openai-java-sdk");
        }
        this.name = builder.name;
    }

    public static class MessageEntityBuilder
    {
        public MessageEntityBuilder role(String role)
        {
            MessageModel messageModel = EnumsUtils.getCompleteMessageModel(role);
            if (ObjectUtils.isEmpty(messageModel)) {
                throw new ParamException(String.format("Not support completion role %s", role));
            }
            this.role = role;
            return this;
        }

        public MessageEntityBuilder content(String content)
        {
            if (StringUtils.isEmpty(content)) {
                throw new ParamException("Content must not be empty");
            }
            this.content = content;
            return this;
        }

        public MessageEntity build()
        {
            return new MessageEntity(this);
        }
    }
}
