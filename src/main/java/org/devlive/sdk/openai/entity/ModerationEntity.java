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
import org.devlive.sdk.openai.model.ModerationModel;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModerationEntity
{
    /**
     * The input text to classify
     * 要分类的输入文本
     */
    @JsonProperty(value = "input")
    private List<String> inputs;

    @JsonProperty(value = "model")
    private String model;

    private ModerationEntity(ModerationEntityBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.inputs)) {
            builder.inputs(null);
        }
        this.inputs = builder.inputs;

        if (StringUtils.isEmpty(builder.model)) {
            builder.model(ModerationModel.TEXT_MODERATION_LATEST);
        }
        this.model = builder.model;
    }

    public static class ModerationEntityBuilder
    {
        public ModerationEntityBuilder inputs(List<String> inputs)
        {
            if (ObjectUtils.isEmpty(inputs) || inputs.size() == 0) {
                throw new ParamException("Invalid inputs must be non-empty");
            }
            this.inputs = inputs;
            return this;
        }

        public ModerationEntityBuilder model(ModerationModel model)
        {
            this.model = model.getName();
            return this;
        }

        public ModerationEntity build()
        {
            return new ModerationEntity(this);
        }
    }
}
