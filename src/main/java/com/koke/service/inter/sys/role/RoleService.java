package com.koke.service.inter.sys.role;

import com.koke.model.dto.RoleDTO;
import com.koke.model.entity.sys.role.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectRoles(Role role);

    Role selectRoleById(Long roleId);

    Role getByRoleKey(String roleKey);

    void createRole(RoleDTO roleDTO);

    void updateRole(RoleDTO roleDTO);

    void deleteRolesByIds(List<Long> roleIds);

}
