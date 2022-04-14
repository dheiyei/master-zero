package com.lhj.constant;

import lombok.Getter;

@Getter
public enum MenuType {

    CONTENTS("0", "目录"),
    MENU("1", "菜单"),
    BUTTON("2", "按钮");

    private String value;

    private String label;

    MenuType(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
