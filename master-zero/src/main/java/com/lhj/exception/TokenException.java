package com.lhj.exception;

import com.lhj.constant.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenException extends CustomException {

    public TokenException() {
        super();
    }

    public TokenException(Status status) {
        super(status);
    }

    public TokenException(Integer code) {
        super(code);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Integer code, String message) {
        super(code, message);
    }

}
