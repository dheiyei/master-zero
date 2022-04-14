package com.lhj.controller;

import com.lhj.annotation.EnablePage;
import com.lhj.constant.CommonConstants;
import com.lhj.model.ResultInfo;
import com.lhj.model.dto.RoleDTO;
import com.lhj.model.dto.RoleMenuDTO;
import com.lhj.model.entity.role.Role;
import com.lhj.model.entity.role.RoleMenu;
import com.lhj.service.inter.role.RoleMenuService;
import com.lhj.service.inter.role.RoleService;
import com.lhj.valid.CreateGroup;
import com.lhj.valid.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    private final RoleService roleService;
    private final RoleMenuService roleMenuService;

    /**
     * 获取角色列表
     *
     * @param role
     * @return
     */
    @EnablePage
    @GetMapping
    public ResultInfo<List<Role>> getRoles(Role role) {
        List<Role> roles = roleService.selectRoles(role);
        return ResultInfo.success(roles);
    }

    /**
     * 根据id查询角色
     *
     * @param roleId
     * @return
     */
    @GetMapping("/{roleId}")
    public ResultInfo<Role> getRoleById(@PathVariable("roleId") Long roleId) {
        Role role = roleService.selectRoleById(roleId);
        return ResultInfo.success(role);
    }

    /**
     * 创建角色
     *
     * @param roleDTO
     * @return
     */
    @PostMapping
    public ResultInfo<Void> createRole(@Validated(CreateGroup.class) @RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
        return ResultInfo.success();
    }

    /**
     * 修改角色
     *
     * @param roleDTO
     * @return
     */
    @PutMapping
    public ResultInfo<Void> updateRole(@Validated(UpdateGroup.class) @RequestBody RoleDTO roleDTO) {
        roleService.updateRole(roleDTO);
        return ResultInfo.success();
    }

    /**
     * 根据id删除角色
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping("/{roleIds}")
    public ResultInfo<Void> deleteRolesByIds(@PathVariable("roleIds") List<Long> roleIds) {
        roleService.deleteRolesByIds(roleIds);
        return ResultInfo.success();
    }

    /**
     * 根据id获取角色拥有的菜单权限
     *
     * @param roleId
     * @return
     */
    @GetMapping("/menu/{roleId}")
    public ResultInfo<List<RoleMenu>> getRoleMenuByRoleId(@PathVariable("roleId") Long roleId) {
        List<RoleMenu> roleMenus = roleMenuService.selectRoleMenuByRoleId(roleId);
        return ResultInfo.success(roleMenus);
    }

    /**
     * 给角色分配菜单权限
     *
     * @param roleMenuDTO
     * @return
     */
    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    @PostMapping("/menu")
    public ResultInfo<Void> createRoleMenu(@Valid @RequestBody RoleMenuDTO roleMenuDTO) {
        roleMenuService.createRoleMenu(roleMenuDTO.getRoleId(), roleMenuDTO.getMenuIds());
        return ResultInfo.success();
    }

//    /**
//     * 根据id获取角色单位(数据权限)
//     *
//     * @param roleId
//     * @return
//     */
//    @GetMapping("/unit/{roleId}")
//    public ResultInfo<List<RoleUnit>> getRoleUnitByRoleId(@PathVariable("roleId") Long roleId) {
//        List<RoleUnit> roleUnits = roleUnitService.selectRoleUnitByRoleId(roleId);
//        return ResultInfo.success(roleUnits);
//    }

}
