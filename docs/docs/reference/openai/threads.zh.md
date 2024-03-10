---
title: Threads <span style="font-weight:bold; margin-left:10px; color:red;">Beta</span>
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

### 创建线程

---

```java
ThreadEntity configure = ThreadEntity.builder()
        .build();
client.createThread(configure);
```

返回:

```json
{
  "id": "thread_abc123",
  "object": "thread",
  "created_at": 1699012949,
  "metadata": {}
}
```

### 检索线程

---

```java
String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
client.retrieveThread(threadId);
```

返回:

```json
{
  "id": "thread_abc123",
  "object": "thread",
  "created_at": 1699014083,
  "metadata": {}
}
```

### 修改线程

---

```java
String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
ThreadEntity configure = ThreadEntity.builder()
        .build();
client.updateThread(threadId, configure);
```

返回:

```json
{
  "id": "thread_abc123",
  "object": "thread",
  "created_at": 1699014083,
  "metadata": {}
}
```

### 删除线程

---

```java
String threadId = "thread_lsfBRIATCECds5WYLVXnVcpU";
client.deleteThread(threadId);
```

返回:

```json
{
  "id": "thread_abc123",
  "object": "thread.deleted",
  "deleted": true
}
```
