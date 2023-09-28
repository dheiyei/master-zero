package com.koke.service.impl.role;

import com.koke.constant.CommonConstants;
import com.koke.constant.Constants;
import com.koke.mapper.role.RoleMapper;
import com.koke.model.dto.RoleDTO;
import com.koke.model.entity.role.Role;
import com.koke.service.inter.role.RoleMenuService;
import com.koke.service.inter.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleMenuService roleMenuService;
    private final List<String> SYS_ROLES = Arrays.asList(CommonConstants.ROLE_ADMIN, CommonConstants.ROLE_USER);

    @Override
    public List<Role> selectRoles(Role role) {
        return roleMapper.selectRoles(role);
    }

    @Override
    public Role selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public Role getByRoleKey(String roleKey) {
        return roleMapper.selectByRoleKey(roleKey);
    }

    @Transactional
    @Override
    public void createRole(RoleDTO roleDTO) {
        Assert.isTrue(checkRoleKeyUnique(roleDTO), Constants.ROLE_KEY_EXISTED);
        roleMapper.createRole(roleDTO);
//        //添加角色单位
//        roleUnitService.createRoleUnit(roleDTO.getRoleId(), roleDTO.getUnitIds());
    }

    @Transactional
    @Override
    public void updateRole(RoleDTO roleDTO) {
        Assert.isTrue(checkRoleKeyUnique(roleDTO), Constants.ROLE_KEY_EXISTED);
        Role role = roleMapper.selectRoleById(roleDTO.getRoleId());
        Assert.isTrue(!(role != null && SYS_ROLES.contains(role.getRoleKey()) && !role.getRoleKey().equals(roleDTO.getRoleKey())), Constants.SYS_ROLE_KEY_CAN_NOT_UPDATE);
        roleMapper.updateRole(roleDTO);
//        //添加角色单位
//        roleUnitService.createRoleUnit(roleDTO.getRoleId(), roleDTO.getUnitIds());
    }

    @Transactional
    @Override
    public void deleteRolesByIds(List<Long> roleIds) {
        for (Long roleId : roleIds) {
            Role role = roleMapper.selectRoleById(roleId);
            Assert.isTrue(!SYS_ROLES.contains(role.getRoleKey()), Constants.SYS_ROLE_CAN_NOT_DELETE);
            //删除角色菜单
            roleMenuService.deleteRoleMenuByRoleId(roleId);
//            //删除角色单位
//            roleUnitService.deleteRoleUnitByRoleId(roleId);
        }
        roleMapper.deleteRolesByIds(roleIds);
    }

    /**
     * 校验角色标识是否唯一
     *
     * @param roleDTO
     * @return
     */
    private boolean checkRoleKeyUnique(RoleDTO roleDTO) {
        String roleKey = roleDTO.getRoleKey().toUpperCase();
        if (!roleKey.startsWith(CommonConstants.ROLE_PREFIX)) {
            roleDTO.setRoleKey(roleKey.replace(roleKey, CommonConstants.ROLE_PREFIX + roleKey));
        } else {
            roleDTO.setRoleKey(roleKey);
        }
        Role dbRole = roleMapper.selectByRoleKey(roleDTO.getRoleKey());
        if (dbRole != null && !Objects.equals(dbRole.getRoleId(), roleDTO.getRoleId())) {
            return false;
        }
        return true;
    }

}
