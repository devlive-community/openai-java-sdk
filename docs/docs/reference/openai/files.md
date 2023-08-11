---
title: Files
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

### List files

---

Returns a list of files that belong to the user's organization.

```java
client.files()
```

Returns:

```json
{
  "data": [
    {
      "id": "file-ccdDZrC3iZVNiQVeEA6Z66wf",
      "object": "file",
      "bytes": 175,
      "createdTime": "2022-02-02 22:22:22",
      "filename": "train.jsonl",
      "purpose": "search"
    }
  ],
  "object": "list"
}
```
