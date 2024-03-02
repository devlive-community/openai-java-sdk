---
title: Assistants <span style="font-weight:bold; margin-left:10px; color:red;">Beta</span>
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

### Create assistant file

---

Create an assistant file by attaching a File to an assistant.

```java
client.createAssistantsFile("file-jNuKdx61rNQ0FUhuPFpMNmGZ", "asst_xv9N9dNXstuV8OVLElLqgV7U")
```

Returns:

```json
{
  "id": "file-abc123",
  "object": "assistant.file",
  "created_at": 1699055364,
  "assistant_id": "asst_abc123"
}
```

### List assistants

---

Returns a list of assistants.

```java
client.listAssistants(null);

// With query params
QueryEntity configure = QueryEntity.builder()
        .limit(2)
        .build();
client.assistants(configure);
```

Returns:

```json
{
  "object": "list",
  "data": [
    {
      "id": "asst_abc123",
      "object": "assistant",
      "created_at": 1698982736,
      "name": "Coding Tutor",
      "description": null,
      "model": "gpt-4",
      "instructions": "You are a helpful assistant designed to make me better at coding!",
      "tools": [],
      "file_ids": [],
      "metadata": {}
    }
  ],
  "first_id": "asst_abc123",
  "last_id": "asst_abc789",
  "has_more": false
}
```
