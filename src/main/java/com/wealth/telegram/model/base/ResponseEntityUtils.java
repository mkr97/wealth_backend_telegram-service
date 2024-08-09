package com.wealth.telegram.model.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {
    public ResponseEntityUtils() {
    }

    public static ResponseEntity<APIResponse> clientError(APIResponse apiResponse) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    public static ResponseEntity<APIResponse> serverError(APIResponse apiResponse) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    public static ResponseEntity<APIResponse> serverError(APIResponse apiResponse, HttpStatus status) {
        return ResponseEntity.status(status).body(apiResponse);
    }

    public static <T> ResponseEntity<APIResponse<T>> success(T data) {
        APIResponse.builder().data(data).build();
        APIResponse apiResponse = APIResponse.success();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    public static ResponseEntity<APIResponse> errorWithStatus(HttpStatus httpStatus, APIResponse apiResponse) {
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

}