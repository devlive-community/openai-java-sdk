package org.devlive.sdk.openai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.openai.exception.ParamException;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmbeddingEntity
{
    /**
     * ID of the model to use. You can use the List models API to see all of your available models, or see our Model overview for descriptions of them.
     */
    @JsonProperty(value = "model")
    private String model;

    /**
     * Input text to embed, encoded as a string or array of tokens. To embed multiple inputs in a single request, pass an array of strings or array of token arrays. Each input must not exceed the max input tokens for the model (8191 tokens for text-embedding-ada-002).
     */
    @JsonProperty(value = "input")
    private String input;

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     */
    @JsonProperty(value = "user")
    private String user;

    /* ====================== Response ====================== */
    @JsonProperty(value = "object")
    private String object;

    @JsonProperty(value = "embedding")
    private List<Object> embeddings;

    @JsonProperty(value = "index")
    private long index;

    private EmbeddingEntity(EmbeddingEntityBuilder builder)
    {
        if (StringUtils.isEmpty(builder.model)) {
            builder.model(null);
        }
        this.model = builder.model;

        if (StringUtils.isEmpty(builder.input)) {
            builder.input(null);
        }
        this.input = builder.input;

        this.user = builder.user;
    }

    public static class EmbeddingEntityBuilder
    {
        public EmbeddingEntityBuilder model(String model)
        {
            if (!model.equals("text-embedding-ada-002")
                    && !(model.startsWith("text-similarity-") && model.endsWith("-001"))
                    && !(model.startsWith("text-search-") && model.endsWith("-001"))
                    && !(model.startsWith("code-search-") && model.endsWith("-001"))) {
                throw new ParamException(String.format("Invalid model %s must be specified, Support text-embedding-ada-002, text-similarity-*-001, text-search-*-*-001, code-search-*-*-001", model));
            }
            this.model = model;
            return this;
        }

        public EmbeddingEntityBuilder input(String input)
        {
            if (StringUtils.isEmpty(input)) {
                throw new ParamException("Invalid input must be not empty");
            }
            this.input = input;
            return this;
        }

        public EmbeddingEntity build()
        {
            return new EmbeddingEntity(this);
        }
    }
}
