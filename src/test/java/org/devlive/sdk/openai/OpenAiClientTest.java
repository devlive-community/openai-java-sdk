package org.devlive.sdk.openai;

import com.google.common.collect.Lists;
import okhttp3.OkHttpClient;
import org.devlive.sdk.openai.entity.AudioEntity;
import org.devlive.sdk.openai.entity.ChatEntity;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.devlive.sdk.openai.entity.EditEntity;
import org.devlive.sdk.openai.entity.EmbeddingEntity;
import org.devlive.sdk.openai.entity.ImageEntity;
import org.devlive.sdk.openai.entity.MessageEntity;
import org.devlive.sdk.openai.entity.ModerationEntity;
import org.devlive.sdk.openai.entity.UserKeyEntity;
import org.devlive.sdk.common.exception.AuthorizedException;
import org.devlive.sdk.common.exception.RequestException;
import org.devlive.sdk.openai.model.CompletionModel;
import org.devlive.sdk.openai.model.EditModel;
import org.devlive.sdk.openai.response.ChatResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenAiClientTest
{
    private OpenAiClient client;
    private String invalidApiKey = "sk-rh";
    private long openApiTimeout = 10;

    @Before
    public void before()
    {
        client = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .build();
    }

    @Test
    public void testBuilder()
    {
        Assert.assertNotNull(client);
    }

    @Test
    public void testClient()
    {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(openApiTimeout, TimeUnit.SECONDS)
                .writeTimeout(openApiTimeout, TimeUnit.SECONDS)
                .readTimeout(openApiTimeout, TimeUnit.SECONDS)
                .build();
        client = OpenAiClient.builder()
                .apiHost(System.getProperty("proxy.host"))
                .apiKey(System.getProperty("proxy.token"))
                .client(okHttpClient)
                .timeout(100)
                .build();
        Assert.assertTrue(client.getModels().getModels().size() > 0);
    }

    @Test
    public void testNoAuthorized()
    {
        try (OpenAiClient client = OpenAiClient.builder()
                .apiKey(invalidApiKey)
                .build()) {
            Assert.assertThrows(AuthorizedException.class, () -> client.getModels());
        }
    }

    @Test
    public void testGetModels()
    {
        Assert.assertTrue(client.getModels().getModels().size() > 0);
    }

    @Test
    public void testGetModel()
    {
        String model = "text-davinci-003";
        Assert.assertNotNull(client.getModel(model));
    }

    @Test
    public void testCreateCompletion()
    {
        CompletionEntity configure = CompletionEntity.builder()
                .model(CompletionModel.TEXT_DAVINCI_003)
                .prompt("How to create a completion")
                .temperature(2D)
                .build();
        Assert.assertTrue(client.createCompletion(configure).getChoices().size() > 0);
    }

    @Test
    public void testCustomizedModel()
    {
        client = OpenAiClient.builder()
                .apiHost(System.getProperty("proxy.host"))
                .apiKey(System.getProperty("openai.token"))
                .model("text-davinci-003")
                .build();

        List<MessageEntity> messages = Lists.newArrayList();
        messages.add(MessageEntity.builder()
                .content("Hello, please show me a jok!")
                .build());

        ChatEntity configure = ChatEntity.builder()
                .messages(messages)
                .model("text-davinci-003")
                .build();
        ChatResponse chatCompletion = client.createChatCompletion(configure);
        String content = chatCompletion.getChoices().get(0).getMessage().getContent();
        // System.out.println(content);
        Assert.assertNotNull(content);
    }

    @Test
    public void testCreateChatCompletion()
    {
        List<MessageEntity> messages = Lists.newArrayList();
        messages.add(MessageEntity.builder()
                .content("Hello, my name is openai-java-sdk")
                .build());

        ChatEntity configure = ChatEntity.builder()
                .messages(messages)
                .build();

        client.createChatCompletion(configure)
                .getChoices()
                .forEach(choice -> messages.add(choice.getMessage()));

        messages.add(MessageEntity.builder()
                .content("What is my name?")
                .build());

        Assert.assertTrue(client.createChatCompletion(configure)
                .getChoices()
                .stream()
                .map(v -> v.getMessage().getContent()).findAny().isPresent());
    }

    @Test
    public void testGetKeys()
    {
        Assert.assertNotNull(client.getKeys());
    }

    @Test
    public void testCreateUserAPIKey()
    {
        UserKeyEntity configure = UserKeyEntity.builder()
                .name("Create first key")
                .action("create")
                .build();
        Assert.assertThrows(RequestException.class, () -> client.createUserAPIKey(configure));
    }

    @Test
    public void testCreateImages()
    {
        ImageEntity configure = ImageEntity.builder()
                .prompt("Create a bus")
                .build();
        Assert.assertTrue(client.createImages(configure).getImages().size() > 0);
    }

    @Test
    public void testEditImages()
    {
        String file = this.getClass().getResource("/logo.png").getFile();
        ImageEntity configure = ImageEntity.builder()
                .prompt("Add hello to image")
                .image(new File(file))
                .isEdit(Boolean.TRUE)
                .build();
        Assert.assertTrue(client.editImages(configure).getImages().size() > 0);
    }

    @Test
    public void testVariationsImages()
    {
        String file = this.getClass().getResource("/logo.png").getFile();
        ImageEntity configure = ImageEntity.builder()
                .image(new File(file))
                .isVariation(Boolean.TRUE)
                .build();
        Assert.assertTrue(client.variationsImages(configure).getImages().size() > 0);
    }

    @Test
    public void testCreateEmbeddings()
    {
        EmbeddingEntity configure = EmbeddingEntity.builder()
                .model("text-similarity-ada-001")
                .input("Hello OpenAi Java SDK")
                .build();
        Assert.assertTrue(client.createEmbeddings(configure).getEmbeddings().size() > 0);
    }

    @Test
    public void testAudioTranscriptions()
    {
        String file = this.getClass().getResource("/hello.mp3").getFile();
        AudioEntity configure = AudioEntity.builder()
                .file(new File(file))
                .build();
        Assert.assertTrue(client.audioTranscriptions(configure)
                .getText()
                .length() > 0);
    }

    @Test
    public void testModerations()
    {
        ModerationEntity configure = ModerationEntity.builder()
                .inputs(Lists.newArrayList("Hello OpenAi Java SDK"))
                .build();
        Assert.assertNotNull(client.moderations(configure));
    }

    @Test
    public void testEdits()
    {
        EditEntity configure = EditEntity.builder()
                .model(EditModel.TEXT_DAVINCI_EDIT_001)
                .input("Hello OpenAi Java SDK")
                .instruction("Fix the spelling mistakes")
                .build();
        Assert.assertNotNull(client.edit(configure));
    }
}
