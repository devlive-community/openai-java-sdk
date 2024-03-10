---
title: Threads <span style="font-weight:bold; margin-left:10px; color:red;">Beta</span>
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

### Create thread

---

```java
ThreadEntity configure = ThreadEntity.builder()
        .build();
client.createThread(configure);
```

Returns:

```json
{
  "id": "thread_abc123",
  "object": "thread",
  "created_at": 1699012949,
  "metadata": {}
}
```

### Retrieve thread

---

```java
String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
client.retrieveThread(threadId);
```

Returns:

```json
{
  "id": "thread_abc123",
  "object": "thread",
  "created_at": 1699014083,
  "metadata": {}
}
```

### Modify thread

---

```java
String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
ThreadEntity configure = ThreadEntity.builder()
        .build();
client.updateThread(threadId, configure);
```

Returns:

```json
{
  "id": "thread_abc123",
  "object": "thread",
  "created_at": 1699014083,
  "metadata": {}
}
```

### Delete thread

---

```java
String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
client.deleteThread(threadId);
```

Returns:

```json
{
  "id": "thread_abc123",
  "object": "thread.deleted",
  "deleted": true
}
```
