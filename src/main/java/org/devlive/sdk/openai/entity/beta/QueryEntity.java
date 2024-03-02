package org.devlive.sdk.openai.entity.beta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.sdk.openai.model.OrderModel;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryEntity
{
    @JsonProperty(value = "limit")
    private Integer limit = 20;

    @JsonProperty(value = "order")
    private OrderModel order = OrderModel.desc;

    @JsonProperty(value = "after")
    private String after;

    @JsonProperty(value = "before")
    private String before;
}
