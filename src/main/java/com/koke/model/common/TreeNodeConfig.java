package com.koke.model.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 树形节点配置
 */
@Data
public class TreeNodeConfig implements Serializable {

    private static final long serialVersionUID = 6024148312895263909L;

    public static TreeNodeConfig DEFAULT_CONFIG = new TreeNodeConfig();

    private String id = "id";
    private String name = "name";
    private String parentId = "parentId";
    private String sort = "sort";
    private String children = "children";

}
