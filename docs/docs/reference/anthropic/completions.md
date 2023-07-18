---
title: Completions
---

!!! note

    Support the claude, product address: [https://claude.ai/](https://claude.ai/)

### Create completion

---

Creates a completion for the provided prompt and parameters.

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

