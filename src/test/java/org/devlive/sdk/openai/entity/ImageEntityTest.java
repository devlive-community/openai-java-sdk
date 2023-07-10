package org.devlive.sdk.openai.entity;

import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.model.ImageSizeModel;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ImageEntityTest
{
    private String prompt = "Enter";

    @Test
    public void testPrompt()
    {
        ImageEntity entity = ImageEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertEquals(entity.getPrompt(), prompt);

        Assert.assertThrows(ParamException.class, () -> CompletionEntity.builder()
                .build());
    }

    @Test
    public void testSize()
    {
        ImageEntity entity = ImageEntity.builder()
                .prompt(prompt)
                .build();
        Assert.assertEquals(entity.getSize(), ImageSizeModel.X_1024.getName());

        entity = ImageEntity.builder()
                .prompt(prompt)
                .size(ImageSizeModel.X_256)
                .build();
        Assert.assertEquals(entity.getSize(), ImageSizeModel.X_256.getName());
    }

    @Test
    public void testImage()
    {
        Assert.assertThrows(ParamException.class, () -> ImageEntity.builder()
                .isEdit(Boolean.TRUE)
                .build());

        String file = this.getClass().getResource("/logo.png").getFile();
        Assert.assertTrue(ImageEntity.builder()
                .prompt("Testing")
                .image(new File(file))
                .isEdit(Boolean.TRUE)
                .build()
                .getIsEdit());
    }
}