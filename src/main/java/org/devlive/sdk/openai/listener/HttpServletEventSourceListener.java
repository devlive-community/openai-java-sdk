package org.devlive.sdk.openai.listener;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.openai.exception.ParamException;
import org.devlive.sdk.openai.response.CompleteResponse;
import org.devlive.sdk.openai.utils.JsonUtils;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Builder
public class HttpServletEventSourceListener
        extends EventSourceListener
{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private JsonUtils<CompleteResponse> jsonUtils = JsonUtils.getInstance();

    private HttpServletEventSourceListener(HttpServletEventSourceListenerBuilder builder)
    {
        if (ObjectUtils.isEmpty(builder.request)) {
            builder.request(null);
        }
        this.request = builder.request;

        if (ObjectUtils.isEmpty(builder.response)) {
            builder.response(null);
        }
        this.response = builder.response;
    }

    @Override
    public void onOpen(EventSource eventSource, Response response)
    {
        log.info("HttpServlet listener opened on time {}", LocalDateTime.now());

        this.response.setContentType("text/event-stream");
        this.response.setCharacterEncoding("UTF-8");

        HttpSession session = this.request.getSession();
        session.setAttribute("eventSourceListener", this);
    }

    @Override
    public void onClosed(EventSource eventSource)
    {
        log.info("HttpServlet listener closed on time {}", LocalDateTime.now());
        this.close();
    }

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data)
    {
        if (data.equals("[DONE]")) {
            eventSource.cancel();
            this.close();
        }
        else {
            try {
                CompleteResponse completeResponse = jsonUtils.getObject(data, CompleteResponse.class);
                log.info("HttpServlet event received on time {} id {} type {} data {}", LocalDateTime.now(), id, type, completeResponse.getChoices().get(0).getContent());
                this.response.getWriter().write(completeResponse.getChoices().get(0).getContent());
                this.response.flushBuffer();
            }
            catch (IOException e) {
                log.warn("HttpServlet event error on time {} id {} type {} data {}", LocalDateTime.now(), id, type, data, e);
            }
        }
    }

    @Override
    public void onFailure(EventSource eventSource, Throwable throwable, Response response)
    {
        if (ObjectUtils.isNotEmpty(throwable)) {
            if (throwable.getMessage().endsWith("CANCEL")) {
                log.info("HttpServlet listener cancelled on time {}", LocalDateTime.now());
                this.onClosed(eventSource);
            }
            else {
                log.error("HttpServlet listener throwable \n{}\n response: \n{}\n", throwable, response);
            }
        }
        else {
            log.error("HttpServlet listener failure with empty throwable. Response: \n{}\n", response);
        }
        eventSource.cancel();
        this.close();
    }

    private void close()
    {
        HttpSession session = this.request.getSession();
        if (session != null) {
            session.removeAttribute("eventSourceListener");
        }
    }

    public static class HttpServletEventSourceListenerBuilder
    {
        public HttpServletEventSourceListenerBuilder request(HttpServletRequest request)
        {
            if (ObjectUtils.isEmpty(request)) {
                throw new ParamException("Invalid request instance must not be null");
            }
            this.request = request;
            return this;
        }

        public HttpServletEventSourceListenerBuilder response(HttpServletResponse response)
        {
            if (ObjectUtils.isEmpty(response)) {
                throw new ParamException("Invalid response instance must not be null");
            }
            this.response = response;
            return this;
        }

        public HttpServletEventSourceListener build()
        {
            return new HttpServletEventSourceListener(this);
        }
    }
}
