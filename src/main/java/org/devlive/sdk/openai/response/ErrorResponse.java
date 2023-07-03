package org.devlive.sdk.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse
{
    private String message;
    private String type;
    private String param;
    private String code;
}
