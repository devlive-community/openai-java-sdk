---
title: Completions
---

!!! note

    支持 google palm，产品地址: [https://developers.generativeai.google/products/palm](https://developers.generativeai.google/products/palm)

### Create completion

---

为提供的提示和参数创建补全。

```java
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
