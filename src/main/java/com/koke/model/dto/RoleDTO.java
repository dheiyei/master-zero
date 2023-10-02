package com.koke.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * 角色请求类
 * @author koke
 */
@Data
@ApiModel(description= "角色请求对象")
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -1517362736548499927L;

    @ApiModelProperty(value ="唯一ID",example = "0")
    private Long roleId;

    @NotBlank
    @Pattern(regexp = "^[_a-zA-Z]+$", message = "角色权限标识只能输入英文和下划线")
    @ApiModelProperty(value ="角色标识")
    private String roleKey;

    @ApiModelProperty(value ="角色名")
    private String roleName;

    @ApiModelProperty(value ="角色描述")
    private String description;

    @ApiModelProperty(value ="数据范围")
    private String dataScope;

    @ApiModelProperty(value ="部门id列表")
    private List<Long> departmentIds;

}
