package com.koke.constant;

import lombok.Getter;

/**
 * @author 日志枚举
 */

@Getter
public enum LogState {

    /**
     * 成功
     */
    SUCCESS("0", "成功"),

    /**
     * 失败
     */
    ERROR("1", "失败");

    private final String state;

    private final String description;

    LogState(String state, String description) {
        this.state = state;
        this.description = description;
    }

}
