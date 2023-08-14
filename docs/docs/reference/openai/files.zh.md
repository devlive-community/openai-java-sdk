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

返回:

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

### Upload file

---

上传包含要在各种端点/功能之间使用的文档的文件。目前，一个组织上传的所有文件大小最大可达1GB。如果您需要增加存储限制，请联系 OpenAI。

```java
String file = this.getClass().getResource("/test.jsonl").getFile();
FileEntity configure = FileEntity.builder()
    .file(new File(file))
    .build();
this.client.uploadFile(configure);
```

返回:

```json
{
  "id": "file-XjGxS3KTG0uNmNOK362iJua3",
  "object": "file",
  "bytes": 140,
  "createdTime": "2022-02-02 22:22:22",
  "filename": "test.jsonl",
  "purpose": "fine-tune"
}
```

### Delete file

---

删除文件

```java
String id = "file-XjGxS3KTG0uNmNOK362iJua3";
this.client.deleteFile(entity.getId());
```

返回:

```json
{
  "id": "file-XjGxS3KTG0uNmNOK362iJua3",
  "object": "file",
  "deleted": true
}
```

### Retrieve file

---

返回有关特定文件的信息。

```java
String id = "file-XjGxS3KTG0uNmNOK362iJua3";
this.client.retrieveFile(entity.getId());
```

返回:

```json
{
  "id": "file-XjGxS3KTG0uNmNOK362iJua3",
  "object": "file",
  "bytes": 140,
  "created_at": 1613779657,
  "filename": "test.jsonl",
  "purpose": "fine-tune"
}
```
