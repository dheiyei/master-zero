package com.koke.service.impl.sys.role;

import com.koke.mapper.sys.role.RoleMenuMapper;
import com.koke.model.entity.sys.role.RoleMenu;
import com.koke.service.inter.sys.role.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleMenu> selectRoleMenuByRoleId(Long roleId) {
        return roleMenuMapper.selectRoleMenuByRoleId(roleId);
    }

    @Transactional
    @Override
    public void createRoleMenu(Long roleId, List<Long> menuIds) {
        //删除角色菜单
        deleteRoleMenuByRoleId(roleId);
        //添加角色菜单
        if (!CollectionUtils.isEmpty(menuIds) && roleId != null) {
            roleMenuMapper.createRoleMenu(roleId, menuIds);
        }
    }

    @Transactional
    @Override
    public void deleteRoleMenuByRoleId(Long roleId) {
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
    }

}
