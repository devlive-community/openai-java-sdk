package org.devlive.sdk.openai.entity.beta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadEntity
{
    /**
     * The identifier, which can be referenced in API endpoints.
     * 标识符，可在 API 终结点中引用。
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * The object type, which is always thread.
     * 对象类型，始终为 thread。
     */
    @JsonProperty(value = "object")
    private String object;

    /**
     * The Unix timestamp (in seconds) for when the thread was created.
     * 创建线程时的 Unix 时间戳（以秒为单位）。
     */
    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdTime;

    /**
     * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.
     * 可附加到对象的 16 个键值对的集合。这对于以结构化格式存储有关对象的其他信息非常有用。键的最大长度为 64 个字符，值的最大长度为 512 个字符。
     */
    @JsonProperty(value = "metadata")
    private Map<Object, Object> metadata;

    @JsonProperty(value = "deleted")
    private boolean deleted;
}
