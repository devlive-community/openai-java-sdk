---
title: Edits
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

### Create edit

---

Creates a new edit for the provided input, instruction, and parameters.

```java
EditEntity configure = EditEntity.builder()
        .model(EditModel.TEXT_DAVINCI_EDIT_001)
        .input("Hello OpenAi Java SDK")
        .instruction("Fix the spelling mistakes")
        .build();
client.edit(configure);
```

Returns

```json
{
  "object": "edit",
  "created": 1589478378,
  "choices": [
    {
      "text": "What day of the week is it?",
      "index": 0,
    }
  ],
  "usage": {
    "prompt_tokens": 25,
    "completion_tokens": 32,
    "total_tokens": 57
  }
}
```
