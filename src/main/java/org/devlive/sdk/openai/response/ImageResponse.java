package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.devlive.sdk.openai.entity.ImageEntity;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResponse
{
    @JsonProperty(value = "data")
    private List<ImageEntity> images;
}
