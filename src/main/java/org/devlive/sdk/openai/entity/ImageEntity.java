package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.ImageFormatModel;
import org.devlive.sdk.openai.model.ImageSizeModel;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageEntity
{
    /**
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    @JsonProperty(value = "prompt")
    private String prompt;

    /**
     * The number of images to generate. Must be between 1 and 10.
     */
    @JsonProperty(value = "n")
    private Integer count;

    /**
     * The size of the generated images.
     *
     * @see ImageSizeModel
     */
    @JsonProperty(value = "size")
    private String size;

    /**
     * The format in which the generated images are returned.
     *
     * @see org.devlive.sdk.openai.model.ImageFormatModel
     */
    @JsonProperty(value = "response_format")
    private String format = "url";

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "url")
    private String url;

    private ImageEntity(ImageEntityBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.prompt)) {
            builder.prompt(null);
        }
        this.prompt = builder.prompt;

        if (ObjectUtils.isEmpty(builder.count)) {
            builder.count(1);
        }
        this.count = builder.count;

        if (ObjectUtils.isEmpty(builder.size)) {
            builder.size(ImageSizeModel.X_1024);
        }
        this.size = builder.size;

        if (ObjectUtils.isEmpty(builder.format)) {
            builder.format(ImageFormatModel.url);
        }
        this.format = builder.format;

        this.user = builder.user;
    }

    public static class ImageEntityBuilder
    {
        public ImageEntityBuilder prompt(String prompt)
        {
            if (StringUtils.isEmpty(prompt)) {
                throw new ParamException("Invalid prompt must not be empty");
            }
            this.prompt = prompt;
            return this;
        }

        public ImageEntityBuilder count(Integer count)
        {
            if (count < 1 || count > 10) {
                throw new ParamException(String.format("Invalid count: %s , between 1 and 10", count));
            }
            this.count = count;
            return this;
        }

        public ImageEntityBuilder size(ImageSizeModel size)
        {
            Object instance = EnumUtils.getEnum(ImageSizeModel.class, size.name());
            if (ObjectUtils.isEmpty(instance)) {
                throw new ParamException(String.format("Invalid size: %s , Must be one of %s", size, ImageSizeModel.values()));
            }
            this.size = size.getName();
            return this;
        }

        public ImageEntityBuilder format(ImageFormatModel format)
        {
            Object instance = EnumUtils.getEnum(ImageFormatModel.class, format.name());
            if (ObjectUtils.isEmpty(instance)) {
                throw new ParamException(String.format("Invalid format: %s , Must be one of %s", format, ImageFormatModel.values()));
            }
            this.format = format.name();
            return this;
        }

        public ImageEntity build()
        {
            return new ImageEntity(this);
        }
    }
}
