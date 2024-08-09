package com.wealth.telegram.exception.handler;

import com.wealth.telegram.exception.code.ErrorMessageCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ApplicationException extends RuntimeException {

    private String title;
    private String message;
    private String code;
    private Object data;
    private ErrorMessageCode errorMessageCode;

    public ApplicationException(ErrorMessageCode errorMessageCode) {
        super(errorMessageCode.getCode());
        this.setError(errorMessageCode);
    }

    private void setError(ErrorMessageCode errorMessageCode) {
        this.errorMessageCode = errorMessageCode;
        this.code = errorMessageCode.getCode();
        this.message = errorMessageCode.getMessage();
    }

    public ApplicationException(String code, String message) {
        super(code);
        this.code = code;
        this.message = message;
    }

}