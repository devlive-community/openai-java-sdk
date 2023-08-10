package org.devlive.sdk.openai;

import org.junit.Assert;
import org.junit.Test;

public class FileClientTest
        extends BaseTest
{
    @Test
    public void testFiles()
    {
        Assert.assertNotNull(this.client.files());
    }
}
