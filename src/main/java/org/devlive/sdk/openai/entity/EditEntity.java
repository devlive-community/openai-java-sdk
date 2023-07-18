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
import org.devlive.sdk.openai.model.EditModel;

import java.util.Arrays;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Deprecated
public class EditEntity
{
    /**
     * ID of the model to use. You can use the text-davinci-edit-001 or code-davinci-edit-001 model with this endpoint.
     * 要使用的模型的 ID。您可以对此端点使用 text-davinci-edit-001 或 code-davinci-edit-001 模型。
     *
     * @see org.devlive.sdk.openai.model.EditModel
     */
    @JsonProperty(value = "model")
    private String model;

    /**
     * The input text to use as a starting point for the edit.
     * 用作编辑起点的输入文本。
     */
    @JsonProperty(value = "input")
    private String input;

    /**
     * The instruction that tells the model how to edit the prompt.
     * 告诉模型如何编辑提示的指令。
     */
    @JsonProperty(value = "instruction")
    private String instruction;

    /**
     * How many edits to generate for the input and instruction.
     * 为输入和指令生成多少编辑。
     */
    @JsonProperty(value = "n")
    private Integer count;

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
     * 使用什么采样温度，介于 0 和 2 之间。较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使其更加集中和确定性。
     * <p>
     * We generally recommend altering this or top_p but not both.
     * 我们通常建议更改此值或 top_p，但不要同时更改两者。
     */
    @JsonProperty(value = "temperature")
    private Double temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     * 温度采样的替代方法称为核采样，其中模型考虑具有 top_p 概率质量的标记的结果。因此 0.1 意味着仅考虑包含前 10% 概率质量的标记。
     * <p>
     * We generally recommend altering this or temperature but not both.
     * 我们通常建议更改此值或温度，但不能同时更改两者。
     */
    @JsonProperty(value = "top_p")
    private Double topP;

    private EditEntity(EditEntityBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.model)) {
            throw new ParamException(String.format("Invalid model, Must be one of %s", Arrays.toString(EditModel.values())));
        }
        this.model = builder.model;

        if (StringUtils.isEmpty(builder.input)) {
            builder.input(null);
        }
        this.input = builder.input;

        if (StringUtils.isEmpty(builder.instruction)) {
            builder.instruction(null);
        }
        this.instruction = builder.instruction;

        if (ObjectUtils.isEmpty(builder.count)) {
            builder.count(1);
        }
        this.count = builder.count;

        if (ObjectUtils.isEmpty(builder.temperature)) {
            builder.temperature(1D);
        }
        this.temperature = builder.temperature;

        if (ObjectUtils.isEmpty(builder.topP)) {
            builder.topP(1D);
        }
        this.topP = builder.topP;
    }

    public static class EditEntityBuilder
    {
        public EditEntityBuilder model(EditModel model)
        {
            this.model = model.getName();
            return this;
        }

        public EditEntityBuilder input(String input)
        {
            if (StringUtils.isEmpty(input)) {
                throw new ParamException("Invalid input must be not empty");
            }
            this.input = input;
            return this;
        }

        public EditEntityBuilder instruction(String instruction)
        {
            if (StringUtils.isEmpty(instruction)) {
                throw new ParamException("Invalid instruction must be not empty");
            }
            this.instruction = instruction;
            return this;
        }

        public EditEntityBuilder temperature(Double temperature)
        {
            if (temperature < 0 || temperature > 2) {
                throw new ParamException(String.format("Invalid temperature: %s , between 0 and 2", temperature));
            }
            this.temperature = temperature;
            return this;
        }

        public EditEntity build()
        {
            return new EditEntity(this);
        }
    }
}
