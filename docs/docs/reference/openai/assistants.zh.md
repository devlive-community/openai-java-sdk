---
title: Assistants <span style="font-weight:bold; margin-left:10px; color:red;">Beta</span>
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

### 创建助手

---

```java
AssistantsEntity entity = AssistantsEntity.builder()
        .name("Math Tutor")
        .model(CompletionModel.GPT_35_TURBO)
        .instructions("You are a personal math tutor. When asked a question, write and run Python code to answer the question.")
        .build();
client.createAssistants(entity);
```

返回:

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

### 创建助手文件

---

```java
client.createAssistantsFile("file-jNuKdx61rNQ0FUhuPFpMNmGZ","asst_xv9N9dNXstuV8OVLElLqgV7U")
```

返回:

```json
{
  "id": "file-abc123",
  "object": "assistant.file",
  "created_at": 1699055364,
  "assistant_id": "asst_abc123"
}
```

### 列出助手

---

```java
client.listAssistants(null);

// With query params
QueryEntity configure = QueryEntity.builder()
        .limit(2)
        .build();
client.assistants(configure);
```

返回:

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

### 列出助手文件

---

```java
client.assistantsFiles("asst_xv9N9dNXstuV8OVLElLqgV7U"));
```

返回:

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

### 检索助手

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

### 检索助手文件

---

```java
client.retrieveAssistantFile("asst_xv9N9dNXstuV8OVLElLqgV7U","file-jNuKdx61rNQ0FUhuPFpMNmGZ");
```

返回:

```json
{
  "id": "file-abc123",
  "object": "assistant.file",
  "created_at": 1699055364,
  "assistant_id": "asst_abc123"
}
```

### 修改助手

---

```java
AssistantsEntity entity = AssistantsEntity.builder()
        .name("Math Tutor 1")
        .model(CompletionModel.GPT_35_TURBO)
        .instructions("You are a personal math tutor. When asked a question, write and run Python code to answer the question.")
        .build();
client.updateAssistant("asst_xv9N9dNXstuV8OVLElLqgV7U",entity);
```

返回:

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

### 删除助手

---

```java
client.deleteAssistant("asst_xv9N9dNXstuV8OVLElLqgV7U");
```

返回:

```json
{
  "id": "asst_abc123",
  "object": "assistant.deleted",
  "deleted": true
}
```

### 删除助手文件

---

```java
client.deleteAssistantFile("asst_xv9N9dNXstuV8OVLElLqgV7U","file-jNuKdx61rNQ0FUhuPFpMNmGZ");
```

返回:

```json
{
  "id": "file-abc123",
  "object": "assistant.file.deleted",
  "deleted": true
}
```
