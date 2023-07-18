---
title: Completions
---

!!! note

    支持 claude，产品地址: [https://claude.ai/](https://claude.ai/)

### Create completion

---

为提供的提示和参数创建补全。

```java
// Automatic resource release
try(OpenAiClient client=OpenAiClient.builder()
        .apiKey(System.getProperty("claude.token"))
        .provider(ProviderModel.CLAUDE)
        .build())
        {
    CompletionEntity configure = CompletionEntity.builder()
        .model(CompletionModel.CLAUDE_2.getName())
        .prompt("How to create a completion")
        .build();
    client.createCompletion(configure).getChoices();
}
```
