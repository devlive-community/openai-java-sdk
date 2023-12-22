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
import org.devlive.sdk.openai.model.CompletionModel;

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

    /**
     * The name of the model to fine-tune.
     * 要微调的模型的名称。
     */
    @JsonProperty(value = "model")
    private String model;

    /**
     * The ID of an uploaded file that contains training data.
     * 包含训练数据的已上传文件的 ID。
     */
    @JsonProperty(value = "training_file")
    private String file;

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
            builder.limit(null);
        }
        this.limit = builder.limit;
        this.after = builder.after;

        if (builder.model == null) {
            builder.model(CompletionModel.GPT_35_TURBO);
        }
        this.model = builder.model;

        if (builder.file == null) {
            builder.file(null);
        }
        this.file = builder.file;
    }

    public static class FineTuningEntityBuilder
    {
        public FineTuningEntityBuilder limit(Integer limit)
        {
//            if (limit == null) {
//                limit = 20;
//            }
//
//            if (limit < 1) {
//                throw new ParamException("Invalid limit must not be less than 1");
//            }
            this.limit = limit;
            return this;
        }

        public FineTuningEntityBuilder after(String after)
        {
            this.after = after;
            return this;
        }

        public FineTuningEntityBuilder model(CompletionModel model)
        {
            if (model == null) {
                model = CompletionModel.GPT_35_TURBO;
            }

            switch (model) {
                case GPT_35_TURBO:
                case GPT_35_TURBO_0613:
                case BABBAGE_002:
                case DAVINCI_002:
                case GPT_4_0613:
                    this.model = model.getName();
                    break;
                default:
                    throw new ParamException(String.format("Not support completion model %s", model));
            }
            return this;
        }

        public FineTuningEntityBuilder file(String file)
        {
            if (file == null) {
                throw new ParamException("Invalid file name must not be empty");
            }
            this.file = file;
            return this;
        }

        public FineTuningEntity build()
        {
            return new FineTuningEntity(this);
        }
    }
}
