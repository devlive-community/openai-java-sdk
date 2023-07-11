---
title: Images
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

### Create images

---

Creates an image given a prompt.

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

Creates an edited or extended image given an original image and a prompt.

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

Creates a variation of a given image.

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

