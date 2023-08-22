---
title: Chat
---

!!! note

    支持 google palm，产品地址: [https://developers.generativeai.google/products/palm](https://developers.generativeai.google/products/palm)

### Create chat

---

为给定的聊天对话创建模型响应。

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
