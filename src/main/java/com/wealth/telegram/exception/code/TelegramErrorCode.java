package com.wealth.telegram.exception.code;

import lombok.Getter;

@Getter
public enum TelegramErrorCode implements ErrorMessageCode {

    ACTION_NOT_FOUND("TEC-0001", "Command is not found.");

    private String code;
    private String message;

    private TelegramErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

