package com.koke.mapper.sys.role;

import com.koke.model.entity.sys.role.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper {

    List<RoleMenu> selectRoleMenuByRoleId(Long roleId);

    void createRoleMenu(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

    void deleteRoleMenuByRoleId(Long roleId);

}
