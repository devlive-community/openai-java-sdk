package org.devlive.sdk.openai.utils;

import org.devlive.sdk.openai.model.UrlModel;
import org.junit.Assert;
import org.junit.Test;

public class ProviderUtilsTest
{
    @Test
    public void testGetUrl()
    {
        Assert.assertEquals(ProviderUtils.getUrl(null, UrlModel.FETCH_MODELS), "v1/models");
    }
}
