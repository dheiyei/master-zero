package com.koke.constant;

import lombok.Getter;

/**
 * @author 菜单枚举
 */
@Getter
public enum MenuType {

    //目录
    CONTENTS("0", "目录"),
    //菜单
    MENU("1", "菜单"),
    //按钮
    BUTTON("2", "按钮");

    private final String value;

    private final String label;

    MenuType(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
