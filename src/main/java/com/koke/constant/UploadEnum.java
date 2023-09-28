package com.koke.constant;

import lombok.Getter;

@Getter
public enum UploadEnum {
    OIL(0),

    UNIT(1);

    private int code;

    UploadEnum(int code) {
        this.code = code;
    }
}
