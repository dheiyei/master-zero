package com.koke.exception;

import com.koke.constant.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义全局异常
 */
@Getter
@Setter
public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException() {
        super(Status.INTERNAL_SERVER_ERROR.getMessage());
        this.code = Status.INTERNAL_SERVER_ERROR.getCode();
    }

    public CustomException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
    }

    public CustomException(Integer code) {
        this.code = code;
    }

    public CustomException(String message) {
        super(message);
        this.code = Status.INTERNAL_SERVER_ERROR.getCode();
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
