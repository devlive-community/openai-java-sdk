---
title: Console
---

!!! Note

    Please build the client before calling, the build code is as follows:

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

    `System.getProperty("openai.token")` is the key to access the API authorization.

### Create completion

---

Creates a completion for the provided prompt and parameters.

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

Creates a model response for the given chat conversation.

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
