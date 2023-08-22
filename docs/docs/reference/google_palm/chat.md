---
title: Chat
---

!!! note

    Support the google palm, product address: [https://developers.generativeai.google/products/palm](https://developers.generativeai.google/products/palm)

### Create chat

---

Creates a model response for the given chat conversation.

```java
try(OpenAiClient client=OpenAiClient.builder()
        .provider(ProviderModel.GOOGLE_PALM)
        .model(CompletionModel.CHAT_BISON_001)
        .apiKey(System.getProperty("google.token"))
        .build())
{
    List<MessageEntity> messages = Lists.newArrayList();
    messages.add(MessageEntity.builder()
        .content("Hello, my name is openai-java-sdk")
        .build());

    PromptEntity prompt = PromptEntity.builder()
        .messages(messages)
        .build();

    ChatEntity configure = ChatEntity.builder()
        .prompt(prompt)
        .build();

    client.createPaLMChat(configure)
        .getCandidates()
        .forEach(System.out::println);
}
```

