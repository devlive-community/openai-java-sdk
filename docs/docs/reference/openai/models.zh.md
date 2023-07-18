---
title: Models
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

### List models

---

列出当前可用的模型，并提供每个模型的基本信息，例如所有者和可用性。

```java
client.getModels();
```

Returns:

```json
{
  "data": [
    {
      "id": "model-id-0",
      "object": "model",
      "owned_by": "organization-owner",
      "permission": [...]
    }
  ],
  "object": "list"
}
```

### Retrieve model

---

检索模型实例，提供有关模型的基本信息，例如所有者和权限。

```java
String model = "text-davinci-003";
ModelEntity entity = client.getModel(model);
```

Params:

|  Name   |   Type   | Required |
|:-------:|:--------:|----------|
| `model` | `String` | Yes      |

Returns:

```json
{
  "id": "text-davinci-003",
  "object": "model",
  "owned_by": "openai",
  "permission": [...]
}
```
