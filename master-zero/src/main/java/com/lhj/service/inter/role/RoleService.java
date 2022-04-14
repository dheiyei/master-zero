package com.lhj.service.inter.role;

import com.lhj.model.dto.RoleDTO;
import com.lhj.model.entity.role.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectRoles(Role role);

    Role selectRoleById(Long roleId);

    Role getByRoleKey(String roleKey);

    void createRole(RoleDTO roleDTO);

    void updateRole(RoleDTO roleDTO);

    void deleteRolesByIds(List<Long> roleIds);

}
