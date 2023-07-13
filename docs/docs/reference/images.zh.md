---
title: Images
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

### Create images

---

根据提示创建图像。

```java
ImageEntity configure=ImageEntity.builder()
        .prompt("Create a bus")
        .build();
client.createImages(configure)
```

Returns

```json
{
  "created": 1589478378,
  "data": [
    {
      "url": "https://..."
    },
    {
      "url": "https://..."
    }
  ]
}
```

### Create image edit

---

在给定原始图像和提示的情况下创建编辑或扩展的图像。

```java
String file=this.getClass().getResource("/logo.png").getFile();
ImageEntity configure=ImageEntity.builder()
        .prompt("Add hello to image")
        .image(new File(file))
        .isEdit(Boolean.TRUE)
        .build();
client.editImages(configure);
```

Returns

```json
{
  "created": 1589478378,
  "data": [
    {
      "url": "https://..."
    },
    {
      "url": "https://..."
    }
  ]
}
```

### Create image variation

---

创建给定图像的变体。

```java
String file=this.getClass().getResource("/logo.png").getFile();
ImageEntity configure=ImageEntity.builder()
        .image(new File(file))
        .isVariation(Boolean.TRUE)
        .build();
client.variationsImages(configure);
```

Return 

```json
{
  "created": 1589478378,
  "data": [
    {
      "url": "https://..."
    },
    {
      "url": "https://..."
    }
  ]
}
```

