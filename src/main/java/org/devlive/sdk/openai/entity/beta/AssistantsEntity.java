package org.devlive.sdk.openai.entity.beta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.CompletionModel;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantsEntity
{
    @JsonProperty(value = "id")
    private String id;

    /**
     * ID of the model to use. You can use the List models API to see all of your available models, or see our Model overview for descriptions of them.
     * 要使用的模型的 ID。您可以使用列表模型 API 查看所有可用模型，或查看我们的模型概述以获取这些模型的说明。
     */
    @JsonProperty(value = "model")
    private String model;

    /**
     * The name of the assistant. The maximum length is 256 characters.
     * 助理的名称。最大长度为 256 个字符。
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * The description of the assistant. The maximum length is 512 characters.
     * 助手的描述。最大长度为 512 个字符。
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * The system instructions that the assistant uses. The maximum length is 32768 characters.
     * 助手使用的系统指令。最大长度为 32768 个字符。
     */
    @JsonProperty(value = "instructions")
    private String instructions;

    /**
     * A list of tool enabled on the assistant. There can be a maximum of 128 tools per assistant. Tools can be of types code_interpreter, retrieval, or function.
     * 助手上启用的工具列表。每个助手最多可以有 128 个工具。工具可以是code_interpreter、检索或函数类型。
     */
    @JsonProperty(value = "tools")
    private Set<Map<String, String>> tools = Sets.newHashSet();

    /**
     * A list of file IDs attached to this assistant. There can be a maximum of 20 files attached to the assistant. Files are ordered by their creation date in ascending order.
     * 附加到此助手的文件 ID 列表。助手最多可以附加 20 个文件。文件按其创建日期升序排序。
     */
    @JsonProperty(value = "file_ids")
    private Set<String> fileIds = Sets.newHashSet();

    /**
     * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.
     * 可附加到对象的 16 个键值对的集合。这对于以结构化格式存储有关对象的其他信息非常有用。键的最大长度为 64 个字符，值的最大长度为 512 个字符。
     */
    @JsonProperty(value = "metadata")
    private Map<String, String> metadata;

    public AssistantsEntity(AssistantsEntityBuilder builder)
    {
        this.model = builder.model;
        this.name = builder.name;
        this.description = builder.description;
        this.instructions = builder.instructions;
        this.tools = builder.tools;
        this.fileIds = builder.fileIds;
        this.metadata = builder.metadata;
    }

    public static class AssistantsEntityBuilder
    {
        public AssistantsEntityBuilder model(CompletionModel model)
        {
            if (model == null) {
                throw new ParamException("Model cannot be null");
            }

            this.model = model.getName();
            return this;
        }

        public AssistantsEntityBuilder name(String name)
        {
            if (name.length() > 256) {
                throw new ParamException("Name cannot be longer than 256 characters");
            }

            this.name = name;
            return this;
        }

        public AssistantsEntityBuilder description(String description)
        {
            if (description.length() > 512) {
                throw new ParamException("Description cannot be longer than 512 characters");
            }

            this.description = description;
            return this;
        }

        public AssistantsEntityBuilder instructions(String instructions)
        {
            if (instructions.length() > 32768) {
                throw new ParamException("Instructions cannot be longer than 32768 characters");
            }

            this.instructions = instructions;
            return this;
        }

        public AssistantsEntityBuilder tools(Set<Map<String, String>> tools)
        {
            if (tools.size() > 128) {
                throw new ParamException("Tools cannot be longer than 128 characters");
            }

            this.tools = tools;
            return this;
        }

        public AssistantsEntityBuilder fileIds(Set<String> fileIds)
        {
            if (fileIds.size() > 20) {
                throw new ParamException("FileIds cannot be longer than 20 characters");
            }

            this.fileIds = fileIds;
            return this;
        }

        public AssistantsEntity build()
        {
            return new AssistantsEntity(this);
        }
    }
}
