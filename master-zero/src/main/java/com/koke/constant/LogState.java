package com.koke.constant;

import lombok.Getter;

@Getter
public enum LogState {

    SUCCESS("0", "成功"),

    ERROR("1", "失败");

    private String state;

    private String description;

    LogState(String state, String description) {
        this.state = state;
        this.description = description;
    }

}
