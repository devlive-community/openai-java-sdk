---
title: Models
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

### List models

---

Lists the currently available models, and provides basic information about each one such as the owner and availability.

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

Retrieves a model instance, providing basic information about the model such as the owner and permissioning.

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
