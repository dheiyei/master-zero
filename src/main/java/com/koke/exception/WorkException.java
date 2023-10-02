package com.koke.exception;

import com.koke.constant.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * 事务异常
 * @author koke
 */
@Getter
@Setter
public class WorkException extends RuntimeException{

    private Integer code;

    public WorkException() {
        super(Status.INTERNAL_SERVER_ERROR.getMessage());
        this.code = Status.INTERNAL_SERVER_ERROR.getCode();
    }

    public WorkException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
    }

    public WorkException(Integer code) {
        this.code = code;
    }

    public WorkException(String message) {
        super(message);
        this.code = Status.INTERNAL_SERVER_ERROR.getCode();
    }

    public WorkException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
