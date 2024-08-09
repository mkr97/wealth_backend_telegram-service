package com.wealth.telegram.exception.handler.advice;

import com.wealth.telegram.exception.code.AppErrorCode;
import com.wealth.telegram.exception.handler.ApplicationException;
import com.wealth.telegram.model.base.APIResponse;
import com.wealth.telegram.model.base.ResponseEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<APIResponse> handleGeneralException(Exception ex, WebRequest request) {
        log.error("The application got an error: ", ex);
        String code = AppErrorCode.INTERNAL_SYSTEM_ERROR.getCode();
        String message = AppErrorCode.INTERNAL_SYSTEM_ERROR.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Throwable cause = ex.getCause() == null ? ex : ex.getCause();
        if (cause instanceof HttpMediaTypeNotSupportedException | cause instanceof HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
            code = "HTTP405";
            message = ((Throwable)cause).getMessage();
        } else if (cause instanceof ServletRequestBindingException) {
            code = "HTTP400";
            message = ((Throwable)cause).getMessage();
            status = HttpStatus.BAD_REQUEST;
        } else if (cause instanceof HttpMessageNotReadableException) {
            message = ((Throwable)cause).getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        APIResponse<?> apiResponse = APIResponse.builder().code(code).message(message).build();
        return ResponseEntityUtils.serverError(apiResponse, status);
    }

    @ExceptionHandler({ApplicationException.class})
    protected ResponseEntity<APIResponse> handleApplicationException(ApplicationException ex, WebRequest request) {
        log.error("The application got an error: ", ex);
        ApplicationException exception = ex.getCause() == null ? ex : (ApplicationException)ex.getCause();
        APIResponse<Object> apiResponse = this.createDefaltApiResponse(ex, exception.getCode());
        apiResponse.setData(exception.getData());
        return ResponseEntityUtils.clientError(apiResponse);
    }

    private APIResponse<Object> createDefaltApiResponse(Exception ex, String errCode) {
        String message = ex.getMessage();
        return APIResponse.builder().code(errCode).message(message).build();
    }

}
