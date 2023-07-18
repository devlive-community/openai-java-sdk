---
title: Chat Completions
---

!!! Note

    Please build the client before calling, the build code is as follows:

    ```java
    OpenAiClient client = OpenAiClient.builder()
            .apiHost("https://api.openai.com")
            .apiKey(System.getProperty("openai.token"))
            .build();
    ```

    `System.getProperty("openai.token")` is the key to access the API authorization.

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

client.createChatCompletion(configure)
    .getChoices()
    .forEach(choice -> messages.add(choice.getMessage()));

messages.add(CompletionMessageEntity.builder()
    .content("What is my name?")
    .build());

client.createChatCompletion(configure)
    .getChoices()
    .stream()
    .map(v -> v.getMessage().getContent())
    .collect(Collectors.toList())
    .toString()
    .contains("openai-java-sdk");
```

Body:

|        Name        |      Type      | Required |
|:------------------:|:--------------:|----------|
|      `model`       |    `String`    | Yes      |
|      `prompt`      |    `String`    | Yes      |
|   `temperature`    |    `Number`    | No       |
|    `maxTokens`     |    `Number`    | No       |
|       `topP`       |    `Number`    | No       |
|      `bestOf`      |    `Number`    | No       |
| `frequencyPenalty` |    `Number`    | No       |
| `presencePenalty`  |    `Number`    | No       |
|       `stop`       | `List<String>` | No       |

Returns:

```json
{
  "id": "chatcmpl-123",
  "object": "chat.completion",
  "created": 1677652288,
  "choices": [
    {
      "index": 0,
      "message": {
        "role": "assistant",
        "content": "\n\nHello there, how may I assist you today?"
      },
      "finish_reason": "stop"
    }
  ],
  "usage": {
    "prompt_tokens": 9,
    "completion_tokens": 12,
    "total_tokens": 21
  }
}
```
