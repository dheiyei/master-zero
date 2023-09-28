package com.koke.model.entity.role;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 4049971528601829557L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

}
