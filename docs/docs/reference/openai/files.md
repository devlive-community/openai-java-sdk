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

### Upload file

---

Upload a file that contains document(s) to be used across various endpoints/features. Currently, the size of all the files uploaded by one organization can be up to 1 GB. Please contact openai if you need to increase the storage limit.

```java
String file = this.getClass().getResource("/test.jsonl").getFile();
FileEntity configure = FileEntity.builder()
    .file(new File(file))
    .build();
this.client.uploadFile(configure);
```

Returns:

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

Delete a file.

```java
String id = "file-XjGxS3KTG0uNmNOK362iJua3";
        this.client.deleteFile(entity.getId());
```

Returns:

```json
{
  "id": "file-XjGxS3KTG0uNmNOK362iJua3",
  "object": "file",
  "deleted": true
}
```

### Retrieve file

---

Returns information about a specific file.

```java
String id = "file-XjGxS3KTG0uNmNOK362iJua3";
this.client.retrieveFile(entity.getId());
```

Returns:

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
