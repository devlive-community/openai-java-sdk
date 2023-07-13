---
title: Audio
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

### Create translation

---

Translates audio into English.

```java
String file=this.getClass().getResource("/hello.mp3").getFile();
AudioEntity configure = AudioEntity.builder()
        .file(new File(file))
        .build();
client.audioTranscriptions(configure);
```

Returns

```json
{
  "text": "Hello, my name is Wolfgang and I come from Germany. Where are you heading today?"
}
```
