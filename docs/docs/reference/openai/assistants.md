---
title: Assistants (Beta)
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

### Create assistant

---

Create an assistant with a model and instructions.

```java
AssistantsEntity entity = AssistantsEntity.builder()
        .name("Math Tutor")
        .model(CompletionModel.GPT_35_TURBO)
        .instructions("You are a personal math tutor. When asked a question, write and run Python code to answer the question.")
        .build();
client.createAssistants(entity);
```

Returns:

```json
{
  "id": "asst_abc123",
  "object": "assistant",
  "created_at": 1698984975,
  "name": "Math Tutor",
  "description": null,
  "model": "gpt-4",
  "instructions": "You are a personal math tutor. When asked a question, write and run Python code to answer the question.",
  "tools": [
    {
      "type": "code_interpreter"
    }
  ],
  "file_ids": [],
  "metadata": {}
}
```
