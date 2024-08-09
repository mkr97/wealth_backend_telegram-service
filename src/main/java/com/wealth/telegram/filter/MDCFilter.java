package com.wealth.telegram.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class MDCFilter implements Filter {

    private final String TRACE_ID = "trace-id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put(TRACE_ID, this.getTraceId());
        chain.doFilter(request, response);
    }

    private String getTraceId() {
        return Objects.requireNonNullElse(MDC.get(TRACE_ID), UUID.randomUUID().toString());
    }

}
