---
title: Audio
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

### Create translation

---

将音频翻译成默认音频语言。

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
