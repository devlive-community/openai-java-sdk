---
title: 2024.01.3 (latest)
---

|    发布版本     |     发布时间     |
|:-----------:|:------------:|
| `2024.01.3` | `2024-05-17` |

## OpenAi

---

- 支持新模型 `gpt-4o`

## Google Gemini

---

- 支持简单对话

    ```java
    try (GoogleClient client = GoogleClient.builder()
        .apiKey(token)
        .build()) {
        PartEntity part = PartEntity.builder()
            .text("Hello, Open AI Java SDK!")
            .build();
        ObjectEntity object = ObjectEntity.builder()
            .parts(Lists.newArrayList(part))
            .build();
        ChatEntity chat = ChatEntity.builder()
            .contents(Lists.newArrayList(object))
            .build();
    
        ChatResponse response = client.createChatCompletions(chat);
        response.getCandidates()
            .forEach(item -> item.getContent()
                .getParts()
                .forEach(value -> log.info(value.getText())));
    }
    ```
  
- 支持连续对话

    ```java
    List<ObjectEntity> contents = Lists.newArrayList();
    PartEntity part = PartEntity.builder()
            .text("你好，我叫小明")
            .build();
    ObjectEntity object = ObjectEntity.builder()
            .parts(Lists.newArrayList(part))
            .build();
    contents.add(object);
    ChatEntity chat = ChatEntity.builder()
            .contents(contents)
            .build();
    ChatResponse response = client.createChatCompletions(chat);
    response.getCandidates()
            .forEach(item -> item.getContent()
                    .getParts()
                    .forEach(value -> {
                        log.info(value.getText());
    
                        contents.add(ObjectEntity.builder()
                                .role(RoleModel.MODEL)
                                .parts(Lists.newArrayList(PartEntity.builder()
                                        .text(value.getText())
                                        .build()))
                                .build());
                    }));
    
    ObjectEntity newObject = ObjectEntity.builder()
            .parts(Lists.newArrayList(PartEntity.builder()
                    .text("我刚刚说了什么")
                    .build()))
            .build();
    contents.add(newObject);
    ChatEntity newChat = ChatEntity.builder()
            .contents(contents)
            .build();
    client.createChatCompletions(newChat);
    ```

- 支持流式响应

    ```java
    // 构建客户端
    CountDownLatch countDownLatch = new CountDownLatch(1);
    ConsoleEventSourceListener listener = ConsoleEventSourceListener.builder()
            .countDownLatch(countDownLatch)
            .build();
    GoogleClient client = GoogleClient.builder()
            .apiKey(ResourceUtils.getValue("google.token"))
            .listener(listener)
            .build();
    
    List<ObjectEntity> contents = Lists.newArrayList();
    PartEntity part = PartEntity.builder()
            .text("帮我写一万字的作文")
            .build();
    ObjectEntity object = ObjectEntity.builder()
            .parts(Lists.newArrayList(part))
            .build();
            contents.add(object);
    ChatEntity chat = ChatEntity.builder()
            .contents(contents)
            .build();
    client.createChatCompletions(chat);
    try {
        countDownLatch.await();
    }
    catch (InterruptedException e) {
        log.error("Interrupted while waiting", e);
    }
    ```
