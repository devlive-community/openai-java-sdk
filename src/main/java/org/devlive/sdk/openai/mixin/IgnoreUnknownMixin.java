package org.devlive.sdk.openai.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class IgnoreUnknownMixin
{
}
