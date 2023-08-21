---
title: Completions
---

!!! note

    Support the google palm, product address: [https://developers.generativeai.google/products/palm](https://developers.generativeai.google/products/palm)

### Create completion

---

Creates a completion for the provided prompt and parameters.

```java
// Automatic resource release
try(OpenAiClient client=OpenAiClient.builder()
        .provider(ProviderModel.GOOGLE_PALM)
        .model(CompletionModel.TEXT_BISON_001)
        .apiKey(System.getProperty("google.token"))
        .build())
{
    PromptEntity prompt = PromptEntity.builder()
        .text("How to create a completion")
        .build();
    CompletionEntity configure = CompletionEntity.builder()
        .prompt(prompt)
        .build();
    client.createPaLMCompletion(configure).getCandidates();
}
```

