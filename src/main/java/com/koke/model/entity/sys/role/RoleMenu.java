package com.koke.model.entity.sys.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单
 * @author koke
 */
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 4049971528601829557L;

    @ApiModelProperty(value ="角色id",example = "0")
    private Long roleId;

    @ApiModelProperty(value ="菜单id",example = "0")
    private Long menuId;

}
