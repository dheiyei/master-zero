package com.koke.mapper.sys.role;

import com.koke.model.dto.RoleDTO;
import com.koke.model.entity.sys.role.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> selectRolesByUserId(Long userId);

    List<Role> selectRoles(Role role);

    Role selectRoleById(Long roleId);

    Role selectByRoleKey(String roleKey);

    void createRole(RoleDTO roleDTO);

    void updateRole(RoleDTO roleDTO);

    void deleteRolesByIds(List<Long> roleIds);

}
