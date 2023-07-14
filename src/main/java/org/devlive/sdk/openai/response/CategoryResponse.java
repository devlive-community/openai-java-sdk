package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.devlive.sdk.openai.entity.CategoryEntity;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse
{
    @JsonProperty(value = "flagged")
    private Boolean flagged;

    @JsonProperty(value = "categories")
    private CategoryEntity categories;

    @JsonProperty(value = "category_scores")
    private CategoryEntity categoryScores;
}
