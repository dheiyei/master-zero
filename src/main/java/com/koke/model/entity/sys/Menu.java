package com.koke.model.entity.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = -8217256912346829217L;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "单位名称不能为空")
    private String menuName;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 类型 0：目录 1：菜单 2：按钮
     */
    private String type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 删除状态 0：存在 1：删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
