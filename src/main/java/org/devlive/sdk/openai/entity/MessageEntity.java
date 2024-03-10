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

import java.util.Map;
import java.util.Set;

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

    /**
     * A list of File IDs that the message should use. There can be a maximum of 10 files attached to a message. Useful for tools like retrieval and code_interpreter that can access and use files.
     * 邮件应使用的文件 ID 列表。一封邮件最多可以附加 10 个文件。对于可以访问和使用文件的检索和code_interpreter等工具很有用。
     */
    @JsonProperty(value = "file_ids")
    private Set<String> fileIds;

    /**
     * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.
     * 可附加到对象的 16 个键值对的集合。这对于以结构化格式存储有关对象的其他信息非常有用。键的最大长度为 64 个字符，值的最大长度为 512 个字符。
     */
    @JsonProperty(value = "metadata")
    private Map<Object, Object> metadata;

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
        this.fileIds = builder.fileIds;
        this.metadata = builder.metadata;
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
