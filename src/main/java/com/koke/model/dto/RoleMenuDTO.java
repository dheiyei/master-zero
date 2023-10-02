package com.koke.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单请求类
 * @author koke
 */
@Data
@ApiModel(description= "角色菜单请求对象")
public class RoleMenuDTO implements Serializable {

    private static final long serialVersionUID = -7605565837827988848L;

    @NotNull(message = "请选择角色")
    @ApiModelProperty(value ="唯一ID",example = "0")
    private Long roleId;

    @ApiModelProperty(value ="菜单id列表")
    private List<Long> menuIds;

}
