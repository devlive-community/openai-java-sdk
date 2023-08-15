package org.devlive.sdk.openai.listener;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Builder
public class ConsoleEventSourceListener
        extends EventSourceListener
{
    private CountDownLatch countDownLatch;

    @Override
    public void onOpen(EventSource eventSource, Response response)
    {
        log.info("Console listener opened on time {}", LocalDateTime.now());
    }

    @Override
    public void onClosed(EventSource eventSource)
    {
        log.info("Console listener closed on time {}", LocalDateTime.now());
        eventSource.cancel();
        this.close();
    }

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data)
    {
        // OpenAI ends with [DONE] by default
        if (data.equals("[DONE]")) {
            eventSource.cancel();
            this.close();
        }
        else {
            log.info("Console event received on time {} id {} type {} data {}", LocalDateTime.now(), id, type, data);
        }
    }

    @Override
    public void onFailure(EventSource eventSource, Throwable throwable, Response response)
    {
        if (ObjectUtils.isNotEmpty(throwable)) {
            if (throwable.getMessage().endsWith("CANCEL")) {
                log.info("Console listener cancelled on time {}", LocalDateTime.now());
                this.onClosed(eventSource);
            }
            else {
                log.error("Console listener throwable \n{}\n response: \n{}\n", throwable, response);
            }
        }
        else {
            log.error("Console listener failure with empty throwable. Response: \n{}\n", response);
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
