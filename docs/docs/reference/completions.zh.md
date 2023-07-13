---
title: Completions
---

!!! Note

    调用前请先构建客户端，构建代码如下：

    ```java
    OpenAiClient client = OpenAiClient.builder()
            .apiHost("https://api.openai.com")
            .apiKey(System.getProperty("openai.token"))
            .build();
    ```

    `System.getProperty("openai.token")` 是访问 API 授权的关键。

### Create completion

---

为所提供的提示和参数创建一个完整的结果。

```java
CompletionEntity configure = CompletionEntity.builder()
        .model(CompleteModel.TEXT_DAVINCI_003.getName())
        .prompt("How to create a completion")
        .temperature(2D)
        .build();
client.createCompletion(configure);
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
  "id": "cmpl-uqkvlQyYK7bGYrRHQ0eXlWi7",
  "object": "text_completion",
  "created": 1589478378,
  "model": "text-davinci-003",
  "choices": [
    {
      "text": "\n\nThis is indeed a test",
      "index": 0,
      "logprobs": null,
      "finish_reason": "length"
    }
  ],
  "usage": {
    "prompt_tokens": 5,
    "completion_tokens": 7,
    "total_tokens": 12
  }
}
```
