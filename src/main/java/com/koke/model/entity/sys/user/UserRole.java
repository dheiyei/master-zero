package com.koke.model.entity.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色类
 * @author koke
 */
@Data
@ApiModel("用户角色对象")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -6888099542835394413L;

    @ApiModelProperty(value ="用户id",example = "0")
    private Long userId;

    @ApiModelProperty(value ="角色id",example = "0")
    private Long roleId;

}
