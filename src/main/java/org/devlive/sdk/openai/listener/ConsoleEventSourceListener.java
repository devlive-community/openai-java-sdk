package org.devlive.sdk.openai.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Builder
public class ConsoleEventSourceListener
        extends EventSourceListener
{
    private CountDownLatch countDownLatch;
    private JsonUtils<CompleteResponse> jsonUtils;

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response)
    {
        log.info("Console listener opened on time {}", LocalDateTime.now());
        this.jsonUtils = JsonUtils.getInstance();
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource)
    {
        log.info("Console listener closed on time {}", LocalDateTime.now());
        eventSource.cancel();
        this.close();
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data)
    {
        // OpenAI ends with [DONE] by default
        if (data.equals("[DONE]")) {
            eventSource.cancel();
            this.close();
        }
        else {
            try {
                CompleteResponse completeResponse = jsonUtils.getObject(data, CompleteResponse.class);
                log.info("Console event received on time {} id {} type {} data {}", LocalDateTime.now(), id, type, completeResponse.getChoices().get(0).getContent());
            }
            catch (JsonProcessingException e) {
                log.warn("Console event error on time {} id {} type {} data {}", LocalDateTime.now(), id, type, data, e);
            }
        }
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable throwable, @Nullable Response response)
    {
        if (throwable.getMessage().endsWith("CANCEL")) {
            log.info("Console listener cancelled on time {}", LocalDateTime.now());
            this.onClosed(eventSource);
        }
        else {
            log.error("Console listener throwable \n{}\n response: \n{}\n", throwable, response);
        }
        eventSource.cancel();
        this.close();
    }

    private void close()
    {
        if (ObjectUtils.isNotEmpty(this.countDownLatch)) {
            this.countDownLatch.countDown();
        }
    }
}
