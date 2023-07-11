package org.devlive.sdk.openai.model;

public enum CompletionModel
{
    /* ================================ Completion ================================ */
    TEXT_DAVINCI_003("text-davinci-003",
            "Most capable model in the GPT-3 series. Can perform any task the other GPT-3 models can, often with higher quality, longer output and better instruction-following. It can process up to 4,000 tokens per request.",
            "Complex intent, cause and effect, creative generation, search, summarization for audience",
            4096),
    TEXT_CURIE_001("text-curie-001",
            "Very capable, but faster and lower cost than text-davinci-003.",
            "Language translation, complex classification, sentiment, summarization",
            2049),
    TEXT_BABBAGE_001("text-babbage-001",
            "Capable of straightforward tasks, very fast, and lower cost.",
            "Moderate classification, semantic search",
            2049),
    TEXT_ADA_001("text-ada-001",
            "Capable of simple tasks, usually the fastest model in the GPT-3 series, and lowest cost.",
            "Parsing text, simple classification, address correction, keywords",
            2049),
    TEXT_DAVINCI_002("text-davinci-002",
            "Most capable model in the GPT-3 series. Can perform any task the other GPT-3 models can, often with higher quality, longer output and better instruction-following. It can process up to 4,000 tokens per request.",
            "Complex intent, cause and effect, creative generation, search, summarization for audience",
            4097),

    /* ================================ Chat Completion ================================ */
    GPT_4("gpt-4",
            "More capable than any GPT-3.5 model, able to do more complex tasks, and optimized for chat. Will be updated with our latest model iteration 2 weeks after it is released.",
            null,
            8192),
    GPT_4_0613("gpt-4-0613",
            "Snapshot of gpt-4 from June 13th 2023 with function calling data. Unlike gpt-4, this model will not receive updates, and will be deprecated 3 months after a new version is released.",
            null,
            8192),
    GPT_4_32K("gpt-4-32k",
            "Same capabilities as the base gpt-4 mode but with 4x the context length. Will be updated with our latest model iteration.",
            null,
            32768),
    GPT_4_32K_0613("gpt-4-32k-0613",
            "Snapshot of gpt-4-32 from June 13th 2023. Unlike gpt-4-32k, this model will not receive updates, and will be deprecated 3 months after a new version is released.",
            null,
            32768),
    GPT_35_TURBO("gpt-3.5-turbo",
            "Most capable GPT-3.5 model and optimized for chat at 1/10th the cost of text-davinci-003. Will be updated with our latest model iteration 2 weeks after it is released.",
            null,
            4096),
    GPT_35_TURBO_16K("gpt-3.5-turbo-16k",
            "Same capabilities as the standard gpt-3.5-turbo model but with 4 times the context.",
            null,
            16384),
    GPT_35_TURBO_0613("gpt-3.5-turbo-0613",
            "Snapshot of gpt-3.5-turbo from June 13th 2023 with function calling data. Unlike gpt-3.5-turbo, this model will not receive updates, and will be deprecated 3 months after a new version is released.",
            null,
            4096),
    GPT_35_TURBO_16K_0613("gpt-3.5-turbo-16k-0613",
            "Snapshot of gpt-3.5-turbo-16k from June 13th 2023. Unlike gpt-3.5-turbo-16k, this model will not receive updates, and will be deprecated 3 months after a new version is released.",
            null,
            16384),
    CODE_DAVINCI_002("code-davinci-002",
            "Optimized for code-completion tasks",
            null,
            8001),

    TEXT_MODERATION_LATEST("text-moderation-latest",
            "Most capable moderation model. Accuracy will be slighlty higher than the stable model.",
            null,
            Integer.MAX_VALUE),
    TEXT_MODERATION_STABLE("text-moderation-stable",
            "Almost as capable as the latest model, but slightly older.\n",
            null,
            Integer.MAX_VALUE),
    DAVINCI("davinci",
            "Most capable GPT-3 model. Can do any task the other models can do, often with higher quality.",
            null,
            2049),
    CURIE("curie",
            "Very capable, but faster and lower cost than Davinci.",
            null,
            2049),
    BABBAGE("babbage",
            "Capable of straightforward tasks, very fast, and lower cost.",
            null,
            2049),
    ADA("ada",
            "Capable of very simple tasks, usually the fastest model in the GPT-3 series, and lowest cost.",
            null,
            2049);

    private final String name;
    private final String description;
    private final String strengths;
    private final int maxTokens;

    CompletionModel(String name, String description, String strengths, int maxTokens)
    {
        this.name = name;
        this.description = description;
        this.strengths = strengths;
        this.maxTokens = maxTokens;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getStrengths()
    {
        return strengths;
    }

    public int getMaxTokens()
    {
        return maxTokens;
    }
}
