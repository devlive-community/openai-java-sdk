package org.devlive.sdk.openai.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileUtilsTest
{
    @Test
    public void testGetExtension()
    {
        File file = new File("");
        Assert.assertTrue(StringUtils.isEmpty(FileUtils.getExtension(file)));

        file = new File("a.txt");
        Assert.assertTrue(FileUtils.getExtension(file).equals("txt"));
    }
}