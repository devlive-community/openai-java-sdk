---
title: Completions
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

### Create completion

---

Creates a completion for the provided prompt and parameters.

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
    CompletionEntity configure = CompletionEntity.builder()
        .model(CompletionModel.TEXT_DAVINCI_003.getName())
        .prompt("How to create a completion")
        .temperature(2D)
        .build();
    client.createCompletion(configure).getChoices();
}
```

