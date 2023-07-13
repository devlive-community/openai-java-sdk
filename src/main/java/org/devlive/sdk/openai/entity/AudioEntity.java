package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import okhttp3.RequestBody;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.AudioFormatModel;
import org.devlive.sdk.openai.model.AudioModel;
import org.devlive.sdk.openai.utils.FileUtils;
import org.devlive.sdk.openai.utils.MultipartBodyUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AudioEntity
{
    /**
     * The audio file object (not file name) to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * 要转录的音频文件对象（不是文件名），采用以下格式之一：mp3、mp4、mpeg、mpga、m4a、wav 或 webm。
     */
    @JsonProperty(value = "file")
    private File file;

    /**
     * ID of the model to use. Only whisper-1 is currently available.
     * 要使用的模型的 ID。目前只有 whisper-1 可用。
     */
    @JsonProperty(value = "model")
    private String model;

    /**
     * An optional text to guide the model's style or continue a previous audio segment. The prompt should match the audio language.
     * 用于指导模型风格或继续之前的音频片段的可选文本。提示应与音频语言相匹配。
     */
    @JsonProperty(value = "prompt")
    private String prompt;

    /**
     * The format of the transcript output
     * 转录输出的格式
     */
    @JsonProperty(value = "response_format")
    private String format;

    /**
     * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use log probability to automatically increase the temperature until certain thresholds are hit.
     * 采样温度，介于 0 和 1 之间。较高的值（如 0.8）将使输出更加随机，而较低的值（如 0.2）将使其更加集中和确定性。如果设置为 0，模型将使用对数概率自动升高温度，直到达到特定阈值。
     */
    @JsonProperty(value = "temperature")
    private Double temperature;

    /**
     * The language of the input audio. Supplying the input language in ISO-639-1 format will improve accuracy and latency.
     * 输入音频的语言。以 ISO-639-1 格式提供输入语言将提高准确性和延迟。
     */
    @JsonProperty(value = "language")
    private String language;

    public Map<String, RequestBody> convertMap()
    {
        Map<String, RequestBody> map = Maps.newConcurrentMap();
        if (StringUtils.isNotEmpty(this.model)) {
            map.put("model", RequestBody.create(MultipartBodyUtils.TYPE, this.getModel()));
        }
        if (StringUtils.isNotEmpty(this.prompt)) {
            map.put("prompt", RequestBody.create(MultipartBodyUtils.TYPE, this.getPrompt()));
        }
        if (StringUtils.isNotEmpty(this.format)) {
            map.put("response_format", RequestBody.create(MultipartBodyUtils.TYPE, this.getFormat()));
        }
        if (ObjectUtils.isNotEmpty(this.temperature)) {
            map.put("temperature", RequestBody.create(MultipartBodyUtils.TYPE, String.valueOf(this.getTemperature())));
        }
        if (StringUtils.isNotEmpty(this.language)) {
            map.put("language", RequestBody.create(MultipartBodyUtils.TYPE, this.getLanguage()));
        }
        return map;
    }

    private AudioEntity(AudioEntityBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.file)) {
            builder.file(null);
        }
        this.file = builder.file;

        if (ObjectUtils.isEmpty(builder.model)) {
            builder.model("whisper-1");
        }
        this.model = builder.model;

        this.prompt = builder.prompt;

        if (StringUtils.isEmpty(builder.format)) {
            builder.format(AudioFormatModel.json.name());
        }
        this.format = builder.format;

        if (ObjectUtils.isEmpty(builder.temperature)) {
            builder.temperature(1D);
        }
        this.temperature = builder.temperature;

        this.language = builder.language;
    }

    public static class AudioEntityBuilder
    {
        public AudioEntityBuilder file(File file)
        {
            if (ObjectUtils.isEmpty(file)) {
                throw new ParamException("Invalid file must not be empty");
            }

            String extension = FileUtils.getExtension(file);
            if (StringUtils.isEmpty(extension) || ObjectUtils.isEmpty(EnumUtils.getEnum(AudioModel.class, extension.toLowerCase()))) {
                throw new ParamException(String.format("Invalid extension: %s , Must be one of %s", extension, Arrays.toString(AudioModel.values())));
            }
            this.file = file;
            return this;
        }

        public AudioEntityBuilder model(String model)
        {
            if (!model.equals("whisper-1")) {
                throw new ParamException(String.format("Invalid model: %s , Must be only support whisper-1", model));
            }
            this.model = model;
            return this;
        }

        public AudioEntityBuilder format(String format)
        {
            if (ObjectUtils.isEmpty(EnumUtils.getEnum(AudioFormatModel.class, format))) {
                throw new ParamException(String.format("Invalid format: %s , Must be one of %s", format, Arrays.toString(AudioFormatModel.values())));
            }
            this.format = format;
            return this;
        }

        public AudioEntityBuilder temperature(Double temperature)
        {
            if (temperature < 0 || temperature > 2) {
                throw new ParamException(String.format("Invalid temperature: %s , between 0 and 2", temperature));
            }
            this.temperature = temperature;
            return this;
        }

        public AudioEntity build()
        {
            return new AudioEntity(this);
        }
    }
}
