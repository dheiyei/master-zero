package com.lhj.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class RoleMenuDTO implements Serializable {

    private static final long serialVersionUID = -7605565837827988848L;

    /**
     * 角色id
     */
    @NotNull(message = "请选择角色")
    private Long roleId;

    /**
     * 菜单id列表
     */
    private List<Long> menuIds;

}
