package com.koke.model.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 树形节点属性
 */
@Data
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -6001536163407217031L;

    /**
     * 节点id
     */
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 扩展字段
     */
    private Map<String, Object> extra;

}
