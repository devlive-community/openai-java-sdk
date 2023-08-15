package org.devlive.sdk.openai.listener;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.devlive.sdk.openai.OpenAiClient;
import org.devlive.sdk.openai.entity.CompletionEntity;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletEventSourceListenerTest
{
    @Test
    public void test()
            throws IOException
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.when(request.getMethod()).thenReturn("GET");

        HttpSession session = Mockito.mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);

        PrintWriter writer = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(writer);

        HttpServletEventSourceListener listener = HttpServletEventSourceListener.builder()
                .request(request)
                .response(response)
                .build();

        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey(System.getProperty("openai.token"))
                .listener(listener)
                .build();

        CompletionEntity configure = CompletionEntity.builder()
                .prompt("How to create a http servlet stream completion")
                .temperature(2D)
                .build();
        openAiClient.createCompletion(configure);

        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}