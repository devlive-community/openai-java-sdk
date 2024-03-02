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
import org.devlive.sdk.openai.model.ImageFormatModel;
import org.devlive.sdk.openai.model.ImageSizeModel;
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

    @JsonProperty(value = "image")
    private File image;

    @JsonProperty(value = "mask")
    private File mask;

    private Boolean isEdit;
    private Boolean isVariation;

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

        if (ObjectUtils.isEmpty(builder.image)) {
            builder.image(null);
        }
        this.image = builder.image;

        if (ObjectUtils.isEmpty(builder.mask)) {
            builder.mask(null);
        }
        this.mask = builder.mask;

        if (ObjectUtils.isEmpty(builder.isEdit)) {
            builder.isEdit(Boolean.FALSE);
        }
        this.isEdit = builder.isEdit;

        if (ObjectUtils.isEmpty(builder.isVariation)) {
            builder.isVariation(Boolean.FALSE);
        }
        this.isVariation = builder.isVariation;

        this.user = builder.user;
    }

    public Map<String, RequestBody> convertMap()
    {
        Map<String, RequestBody> map = Maps.newConcurrentMap();
        if (this.isEdit) {
            map.put("prompt", RequestBody.create(MultipartBodyUtils.TYPE, this.getPrompt()));
        }
        map.put("n", RequestBody.create(MultipartBodyUtils.TYPE, this.getCount().toString()));
        map.put("size", RequestBody.create(MultipartBodyUtils.TYPE, this.getSize()));
        map.put("response_format", RequestBody.create(MultipartBodyUtils.TYPE, this.getFormat()));

        if (StringUtils.isNotEmpty(this.getUser())) {
            map.put("user", RequestBody.create(MultipartBodyUtils.TYPE, this.getUser()));
        }
        return map;
    }

    public static class ImageEntityBuilder
    {
        public ImageEntityBuilder prompt(String prompt)
        {
            if ((ObjectUtils.isEmpty(this.isVariation) || !this.isVariation) && StringUtils.isEmpty(prompt)) {
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
                throw new ParamException(String.format("Invalid size: %s , Must be one of %s", size, Arrays.toString(ImageSizeModel.values())));
            }
            this.size = size.getName();
            return this;
        }

        public ImageEntityBuilder format(ImageFormatModel format)
        {
            Object instance = EnumUtils.getEnum(ImageFormatModel.class, format.name());
            if (ObjectUtils.isEmpty(instance)) {
                throw new ParamException(String.format("Invalid format: %s , Must be one of %s", format, Arrays.toString(ImageFormatModel.values())));
            }
            this.format = format.name();
            return this;
        }

        public ImageEntityBuilder image(File image)
        {
            if (ObjectUtils.isNotEmpty(image) && image.length() > 4 * 1024 * 102) {
                throw new ParamException("Must be less than 4MB");
            }
            this.image = image;
            return this;
        }

        public ImageEntityBuilder mask(File mask)
        {
            if (ObjectUtils.isNotEmpty(mask) && mask.length() > 4 * 1024 * 102) {
                throw new ParamException("Must be less than 4MB");
            }
            this.mask = mask;
            return this;
        }

        public ImageEntityBuilder isEdit(Boolean isEdit)
        {
            if (isEdit && ObjectUtils.isEmpty(this.image)) {
                throw new ParamException("Image must not be empty.");
            }
            this.isEdit = isEdit;
            return this;
        }

        public ImageEntityBuilder isVariation(Boolean isVariation)
        {
            if (isVariation && ObjectUtils.isNotEmpty(this.prompt)) {
                throw new ParamException("Please remove prompt");
            }

            if (isVariation && ObjectUtils.isEmpty(this.image)) {
                throw new ParamException("Image must not be empty.");
            }
            this.isVariation = isVariation;
            return this;
        }

        public ImageEntity build()
        {
            return new ImageEntity(this);
        }
    }
}
