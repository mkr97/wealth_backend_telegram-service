package com.wealth.telegram.exception.code;

import lombok.Getter;

@Getter
public enum AppErrorCode implements ErrorMessageCode {

    INTERNAL_SYSTEM_ERROR("E00001", "Internal system error"),
    FAILED_READ_FILE("E00002", "An error occurs when reading the file."),
    FAILED_WRITE_FILE("E00003", "An error occurs when writing the file."),
    E00004("E00004", "Token is invalid.");

    private String code;
    private String message;

    private AppErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

