---
title: Chat Completions
---

!!! note

    Support the Open Ai service provided by Microsoft, product address: [https://azure.microsoft.com/zh-cn/products/cognitive-services/openai-service/](https://azure.microsoft.com/zh-cn/products/cognitive-services/openai-service/)

### Required

---

!!! note

    The following are some configurations that must be specified to invoke the service.

|    name    | description                                                                 |
|:----------:|-----------------------------------------------------------------------------|
| `apiHost`  | Created zone markers in the format `${your-resource-name}.openai.azure.com` |  
|  `apiKey`  | Azure token                                                                 |
| `provider` | Specify `ProviderModel.azure`                                               |
|  `model`   | Model name deployed in Azure                                                |
| `version`  | Model version deployed in Azure                                             |

### Create chat completion

---

Creates a model response for the given chat conversation.

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

