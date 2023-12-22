package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.openai.exception.ParamException;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FineTuningEntity
{
    /**
     * Identifier for the last job from the previous pagination request.
     * 上一个分页请求中最后一个作业的标识符。
     */
    @JsonProperty(value = "after")
    private String after;

    /**
     * Number of fine-tuning jobs to retrieve. Defaults to 20
     * 要检索的微调作业数。默认值为 20
     */
    @JsonProperty(value = "limit")
    private Integer limit;

    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    @JsonProperty(value = "level")
    private String level;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "data")
    private String data;

    @JsonProperty(value = "type")
    private String type;

    private FineTuningEntity(FineTuningEntityBuilder builder)
    {
        if (builder.limit == null) {
            builder.limit(20);
        }
        this.limit = builder.limit;
        this.after = builder.after;
    }

    public static class FineTuningEntityBuilder
    {
        public FineTuningEntityBuilder limit(Integer limit)
        {
            if (limit == null) {
                limit = 20;
            }

            if (limit < 1) {
                throw new ParamException("Invalid limit must not be less than 1");
            }
            this.limit = limit;
            return this;
        }

        public FineTuningEntityBuilder after(String after)
        {
            this.after = after;
            return this;
        }

        public FineTuningEntity build()
        {
            return new FineTuningEntity(this);
        }
    }
}
