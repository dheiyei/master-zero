package com.koke.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -1517362736548499927L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色标识
     */
    @NotBlank
    @Pattern(regexp = "^[_a-zA-Z]+$", message = "角色权限标识只能输入英文和下划线")
    private String roleKey;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 部门id列表
     */
    private List<Long> departmentIds;

}
