---
title: Edits
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

### Create edit

---

为提供的输入、指令和参数创建新的编辑。

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
