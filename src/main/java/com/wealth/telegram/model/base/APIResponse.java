package com.wealth.telegram.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.MDC;

public class APIResponse<T> {
    private String code;
    private String title;
    private String message;
    private String traceId = MDC.get("trace-id");
    private T data;

    public APIResponse(String code, String message) {
        this.code = code;
        this.message = message;
        if (getMessage(code) != null) {
            this.message = getMessage(code);
        }

    }

    public APIResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        if (getMessage(code) != null) {
            this.message = getMessage(code);
        }

        this.data = data;
    }

    public APIResponse code(String code) {
        this.code = code;
        this.message = getMessage(code);
        return this;
    }

    /** @deprecated */
    @Deprecated
    public APIResponse message(String message) {
        this.message = message;
        return this;
    }

    public APIResponse data(T data) {
        this.data = data;
        return this;
    }

    static String getMessage(String code) {
        return null;
    }

    static String getMessageKh(String code) {
        return null;
    }

    public static APIResponse success(Object data) {
        return builder().data(data).code("S0001").message("Success.").build();
    }

    public static APIResponse success(Object data, String code) {
        return builder().data(data).code(code).message(getMessage(code)).build();
    }

    public static APIResponse fail(String code) {
        return fail(code, getMessage(code));
    }

    public static APIResponse fail(String code, String message) {
        return fail(code, message, getMessageKh(code));
    }

    public static APIResponse fail(String code, String message, String messageKh) {
        return builder().code(code).message(message).build();
    }

    public static APIResponse success() {
        return builder().code("S0001").message("Success.").build();
    }

    public static <T> APIResponseBuilder<T> builder() {
        return new APIResponseBuilder();
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTraceId() {
        return MDC.get("trace-id");
    }

    public T getData() {
        return this.data;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @JsonProperty("trace_id")
    public void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public APIResponse(final String code, final String title, final String message, final String traceId, final T data) {
        this.code = code;
        this.title = title;
        this.message = message;
        this.traceId = traceId;
        this.data = data;
    }

    public String toString() {
        String var10000 = this.getCode();
        return "APIResponse(code=" + var10000 + ", title=" + this.getTitle() + ", message=" + this.getMessage() + ", traceId=" + this.getTraceId() + ", data=" + this.getData() + ")";
    }

    public APIResponse() {
    }

    public static class APIResponseBuilder<T> {
        private String code;
        private String title;
        private String message;
        private String traceId;
        private T data;

        APIResponseBuilder() {
        }

        public APIResponseBuilder<T> code(final String code) {
            this.code = code;
            return this;
        }

        public APIResponseBuilder<T> title(final String title) {
            this.title = title;
            return this;
        }

        public APIResponseBuilder<T> message(final String message) {
            this.message = message;
            return this;
        }

        @JsonProperty("trace_id")
        public APIResponseBuilder<T> traceId(final String traceId) {
            this.traceId = traceId;
            return this;
        }

        public APIResponseBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        public APIResponse<T> build() {
            return new APIResponse(this.code, this.title, this.message, this.traceId, this.data);
        }

        public String toString() {
            return "APIResponse.APIResponseBuilder(code=" + this.code + ", title=" + this.title + ", message=" + this.message + ", traceId=" + this.traceId + ", data=" + this.data + ")";
        }
    }

}