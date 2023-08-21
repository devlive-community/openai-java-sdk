package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.utils.EnumsUtils;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompletionEntity
{
    @JsonProperty(value = "model")
    private String model;

    @JsonProperty(value = "prompt", required = true)
    private String prompt;

    @JsonProperty(value = "temperature")
    private Double temperature;

    @JsonProperty(value = "max_tokens")
    @JsonAlias(value = {"max_tokens_to_sample"})
    private Integer maxTokens;

    @JsonProperty(value = "top_p")
    private Double topP;

    @JsonProperty(value = "best_of")
    private Integer bestOf;

    @JsonProperty(value = "frequency_penalty")
    private Double frequencyPenalty;

    @JsonProperty(value = "presence_penalty")
    private Double presencePenalty;

    @JsonProperty(value = "stop")
    private List<String> stop;

    /**
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a data: [DONE] message.
     * 是否流回部分进度。如果设置，令牌将在可用时作为仅数据服务器发送事件发送，流由 data: [DONE] 消息终止。
     */
    @JsonProperty(value = "stream")
    private boolean stream = false;

    private CompletionEntity(CompletionEntityBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.model)) {
            builder.model(CompletionModel.TEXT_DAVINCI_003);
        }
        this.model = builder.model;

        if (ObjectUtils.isEmpty(builder.prompt)) {
            builder.prompt(null);
        }
        this.prompt = builder.prompt;

        if (ObjectUtils.isEmpty(builder.temperature)) {
            builder.temperature(1D);
        }
        this.temperature = builder.temperature;

        if (ObjectUtils.isEmpty(builder.maxTokens)) {
            builder.maxTokens(16);
        }
        this.maxTokens = builder.maxTokens;

        if (ObjectUtils.isEmpty(builder.topP)) {
            builder.topP(1D);
        }
        this.topP = builder.topP;

        if (ObjectUtils.isEmpty(builder.bestOf)) {
            builder.bestOf(1);
        }
        this.bestOf = builder.bestOf;

        if (ObjectUtils.isEmpty(builder.frequencyPenalty)) {
            builder.frequencyPenalty(0D);
        }
        this.frequencyPenalty = builder.frequencyPenalty;

        if (ObjectUtils.isEmpty(builder.presencePenalty)) {
            builder.presencePenalty(0D);
        }
        this.presencePenalty = builder.presencePenalty;
        this.stop = builder.stop;
    }

    public static class CompletionEntityBuilder
    {
        public CompletionEntityBuilder model(CompletionModel model)
        {
            if (ObjectUtils.isEmpty(model)) {
                model = CompletionModel.TEXT_DAVINCI_003;
            }
            this.model = model.getName();
            return this;
        }

        public CompletionEntityBuilder prompt(String prompt)
        {
            if (StringUtils.isEmpty(prompt)) {
                throw new ParamException("Invalid prompt must not be empty");
            }
            this.prompt = prompt;
            return this;
        }

        public CompletionEntityBuilder temperature(Double temperature)
        {
            if (temperature < 0 || temperature > 2) {
                throw new ParamException(String.format("Invalid temperature: %s , between 0 and 2", temperature));
            }
            this.temperature = temperature;
            return this;
        }

        public CompletionEntityBuilder maxTokens(Integer maxTokens)
        {
            CompletionModel completionModel = EnumsUtils.getCompleteModel(this.model);
            if (ObjectUtils.isNotEmpty(this.model) && maxTokens > completionModel.getMaxTokens()) {
                throw new ParamException(String.format("Invalid maxTokens: %s, Cannot be larger than the model default configuration %s", maxTokens, completionModel.getMaxTokens()));
            }
            this.maxTokens = maxTokens;
            return this;
        }

        public CompletionEntityBuilder frequencyPenalty(Double frequencyPenalty)
        {
            if (frequencyPenalty < -2.0 || frequencyPenalty > 2.0) {
                throw new ParamException(String.format("Invalid frequencyPenalty: %s , between -2.0 and 2.0", frequencyPenalty));
            }
            this.frequencyPenalty = frequencyPenalty;
            return this;
        }

        public CompletionEntityBuilder presencePenalty(Double presencePenalty)
        {
            if (presencePenalty < -2.0 || presencePenalty > 2.0) {
                throw new ParamException(String.format("Invalid presencePenalty: %s , between -2.0 and 2.0", presencePenalty));
            }
            this.presencePenalty = presencePenalty;
            return this;
        }

        private CompletionEntityBuilder stream()
        {
            return this;
        }

        public CompletionEntity build()
        {
            return new CompletionEntity(this);
        }
    }
}
