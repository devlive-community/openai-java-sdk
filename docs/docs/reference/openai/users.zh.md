---
title: User
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

### List keys

---

列出当前可用的 api 密钥，并提供每个密钥的基本信息，例如所有者和可用性。

```java
client.getKeys();
```

Returns:

```json
{
  "object": "list",
  "data": [
    {
      "sensitive_id": "sk-xxx",
      "object": "api_key",
      "name": "Open API Key",
      "created": 1688363358,
      "last_use": 1688522702,
      "publishable": false
    }
  ]
}
```

### Create a key

---

Create a new key.

```java
UserKeyEntity configure=UserKeyEntity.builder()
        .name("Create first key")
        .action("create")
        .build();
client.createUserAPIKey(configure)
```

Return:

```json
{
  "result": "success",
  "key": {
    "sensitive_id": "sk-xxx",
    "object": "api_key",
    "name": "Test",
    "created": 1688525108,
    "last_use": null,
    "publishable": false
  }
}
```
