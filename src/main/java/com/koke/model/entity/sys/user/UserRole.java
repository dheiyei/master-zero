package com.koke.model.entity.sys.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = -6888099542835394413L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

}
