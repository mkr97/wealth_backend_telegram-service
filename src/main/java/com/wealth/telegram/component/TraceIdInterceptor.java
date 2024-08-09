package com.wealth.telegram.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    public static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = UUID.randomUUID().toString();
        request.setAttribute(TRACE_ID_HEADER, traceId);
        response.setHeader(TRACE_ID_HEADER, traceId);
        return true;
    }

}
