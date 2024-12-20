package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.common.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.utils.EnumsUtils;

import java.util.List;
import java.util.Objects;

import static org.devlive.sdk.openai.model.CompletionModel.GPT_35_TURBO;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatEntity
{
    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "messages")
    private List<MessageEntity> messages;

    @JsonProperty(value = "temperature")
    private Double temperature;

    @JsonProperty(value = "max_tokens")
    private Integer maxTokens;

    @JsonProperty(value = "top_p")
    private Double topP;

    /**
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a data: [DONE] message.
     * 是否流回部分进度。如果设置，令牌将在可用时作为仅数据服务器发送事件发送，流由 data: [DONE] 消息终止。
     */
    @JsonProperty(value = "stream")
    private boolean stream = false;

    private ChatEntity(ChatEntityBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.model)) {
            builder.model(GPT_35_TURBO);
        }
        this.model = builder.model;
        this.messages = builder.messages;

        if (ObjectUtils.isEmpty(builder.temperature)) {
            builder.temperature(1D);
        }
        this.temperature = builder.temperature;

        if (ObjectUtils.isEmpty(builder.maxTokens)) {
            builder.maxTokens(256);
        }
        this.maxTokens = builder.maxTokens;

        if (ObjectUtils.isEmpty(builder.topP)) {
            builder.topP(1D);
        }
        this.topP = builder.topP;
    }

    public static class ChatEntityBuilder
    {
        public ChatEntityBuilder model(CompletionModel model)
        {
            if (ObjectUtils.isEmpty(model)) {
                model = GPT_35_TURBO;
            }
            switch (model) {
                case GPT_35_TURBO:
                case GPT_35_TURBO_16K:
                case GPT_35_TURBO_0613:
                case GPT_35_TURBO_16K_0613:
                case GPT_4:
                case GPT_4_32K:
                case GPT_4_0613:
                case GPT_4_32K_0613:
                case GPT_4O:
                case TEXT_DAVINCI_002:
                case TEXT_DAVINCI_003:
                case CODE_DAVINCI_002:
                    this.model = model.getName();
                    break;
                default:
                    throw new ParamException(String.format("Not support completion model %s", model));
            }
            return this;
        }

        public ChatEntityBuilder model(String model)
        {
            this.model = model;
            return this;
        }

        public ChatEntityBuilder temperature(Double temperature)
        {
            if (temperature < 0 || temperature > 2) {
                throw new ParamException(String.format("Invalid temperature: %s , between 0 and 2", temperature));
            }
            this.temperature = temperature;
            return this;
        }

        public ChatEntityBuilder maxTokens(Integer maxTokens)
        {
            CompletionModel completionModel = EnumsUtils.getCompleteModel(this.model);
            if (Objects.isNull(completionModel)) {
                this.maxTokens = maxTokens;
                return this;
            }
            else {
                if (ObjectUtils.isNotEmpty(this.model)
                        && maxTokens > completionModel.getMaxTokens()) {
                    throw new ParamException(String.format(
                            "Invalid maxTokens: %s, Cannot be larger than the model default configuration %s",
                            maxTokens, completionModel.getMaxTokens()));
                }
                this.maxTokens = maxTokens;
                return this;
            }
        }

        private ChatEntityBuilder stream()
        {
            return this;
        }

        public ChatEntity build()
        {
            return new ChatEntity(this);
        }
    }
}
