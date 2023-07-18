---
title: Completions
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

### Create completion

---

为提供的提示和参数创建补全。

```java
// 自动资源释放
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
