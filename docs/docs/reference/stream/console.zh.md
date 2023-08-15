---
title: Console
---

!!! Note

    调用前请先构建客户端，构建代码如下：

    ```java
    CountDownLatch countDownLatch = new CountDownLatch(1);
    ConsoleEventSourceListener listener = ConsoleEventSourceListener.builder()
            .countDownLatch(countDownLatch)
            .build();
    OpenAiClient client = OpenAiClient.builder()
            .apiKey(System.getProperty("openai.token"))
            .listener(listener)
            .build();
    ```

    `System.getProperty("openai.token")` 是访问 API 授权的关键。

### Create completion

---

为提供的提示和参数创建补全。

```java
CompletionEntity configure = CompletionEntity.builder()
        .model(CompleteModel.TEXT_DAVINCI_003.getName())
        .prompt("How to create a completion")
        .temperature(2D)
        .build();
client.createCompletion(configure);
```

### Create chat completion

---

为给定的聊天对话创建模型响应。

```java
List<CompletionMessageEntity> messages = Lists.newArrayList();
messages.add(CompletionMessageEntity.builder()
    .content("Hello, my name is openai-java-sdk")
    .build());

CompletionChatEntity configure = CompletionChatEntity.builder()
    .messages(messages)
    .build();

client.createChatCompletion(configure);

messages.add(CompletionMessageEntity.builder()
    .content("What is my name?")
    .build());

client.createChatCompletion(configure);
```
