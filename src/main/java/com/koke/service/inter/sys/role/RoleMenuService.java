package com.koke.service.inter.sys.role;

import com.koke.model.entity.sys.role.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    List<RoleMenu> selectRoleMenuByRoleId(Long roleId);

    void createRoleMenu(Long roleId, List<Long> menuIds);

    void deleteRoleMenuByRoleId(Long roleId);

}
