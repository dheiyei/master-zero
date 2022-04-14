package com.lhj.service.inter.role;

import com.lhj.model.entity.role.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    List<RoleMenu> selectRoleMenuByRoleId(Long roleId);

    void createRoleMenu(Long roleId, List<Long> menuIds);

    void deleteRoleMenuByRoleId(Long roleId);

}
