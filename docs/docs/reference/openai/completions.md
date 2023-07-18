---
title: Completions
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
