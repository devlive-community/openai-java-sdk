---
title: User
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

### List keys

---

Lists the currently available api keys, and provides basic information about each one such as the owner and availability.

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
