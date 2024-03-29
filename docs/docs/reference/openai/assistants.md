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

```java
client.createAssistantsFile("file-jNuKdx61rNQ0FUhuPFpMNmGZ","asst_xv9N9dNXstuV8OVLElLqgV7U")
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

### List assistant files

---

```java
client.assistantsFiles("asst_xv9N9dNXstuV8OVLElLqgV7U"));
```

Returns:

```json
{
  "object": "list",
  "data": [
    {
      "id": "file-abc123",
      "object": "assistant.file",
      "created_at": 1699060412,
      "assistant_id": "asst_abc123"
    }
  ],
  "first_id": "file-abc123",
  "last_id": "file-abc456",
  "has_more": false
}
```

### Retrieve assistant

---

```java
client.retrieveAssistant("asst_xv9N9dNXstuV8OVLElLqgV7U");
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

### Retrieve assistant file

---

```java
client.retrieveAssistantFile("asst_xv9N9dNXstuV8OVLElLqgV7U","file-jNuKdx61rNQ0FUhuPFpMNmGZ");
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

### Modify assistant

---

```java
AssistantsEntity entity = AssistantsEntity.builder()
        .name("Math Tutor 1")
        .model(CompletionModel.GPT_35_TURBO)
        .instructions("You are a personal math tutor. When asked a question, write and run Python code to answer the question.")
        .build();
client.updateAssistant("asst_xv9N9dNXstuV8OVLElLqgV7U",entity);
```

Returns:

```json
{
  "id": "asst_abc123",
  "object": "assistant",
  "created_at": 1698984975,
  "name": "Math Tutor 1",
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

### Delete assistant

---

```java
client.deleteAssistant("asst_xv9N9dNXstuV8OVLElLqgV7U");
```

Returns:

```json
{
  "id": "asst_abc123",
  "object": "assistant.deleted",
  "deleted": true
}
```

### Delete assistant file

---

```java
client.deleteAssistantFile("asst_xv9N9dNXstuV8OVLElLqgV7U","file-jNuKdx61rNQ0FUhuPFpMNmGZ");
```

Returns:

```json
{
  "id": "file-abc123",
  "object": "assistant.file.deleted",
  "deleted": true
}
```
