---
title: Chat Completions
---

!!! note

    支持微软提供的 Open Ai服务，产品地址： [https://azure.microsoft.com/zh-cn/products/cognitive-services/openai-service/](https://azure.microsoft.com/zh-cn/products/cognitive-services/openai-service/)

### Required

---

!!! note

    以下是调用服务必须指定的一些配置.

|    name    | description                                         |
|:----------:|-----------------------------------------------------|
| `apiHost`  | 以 `${your-resource-name}.openai.azure.com` 格式创建区域标记 |  
|  `apiKey`  | Azure 令牌                                            |
| `provider` | 指定 `ProviderModel.azure`                            |
|  `model`   | Azure 中部署的模型名称                                      |
| `version`  | Azure 中部署的模型版本                                      |

### Create chat completion

---

为给定的聊天对话创建模型响应。

```java
// Automatic resource release
try(OpenAiClient client=OpenAiClient.builder()
        .apiHost("https://eus-chatgpt.openai.azure.com")
        .apiKey(System.getProperty("azure.token"))
        .provider(ProviderModel.azure)
        .model("text-davinci-002")
        .version("2022-12-01")
        .build())
{
    List<CompletionMessageEntity> messages = Lists.newArrayList();
    messages.add(CompletionMessageEntity.builder()
        .content("Hello, my name is openai-java-sdk")
        .build());

    CompletionChatEntity configure = CompletionChatEntity.builder()
        .messages(messages)
        .build();

    client.createChatCompletion(configure)
        .getChoices()
        .forEach(choice -> messages.add(choice.getMessage()));

    messages.add(CompletionMessageEntity.builder()
        .content("What is my name?")
        .build());
    client.createChatCompletion(configure).getChoices();
}
```

