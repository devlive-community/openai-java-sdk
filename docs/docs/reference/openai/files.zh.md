---
title: Files
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

### List files

---

返回属于用户组织的文件列表。

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
