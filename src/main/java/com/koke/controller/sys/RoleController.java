package com.koke.controller.sys;

import com.koke.aspect.annotation.EnablePage;
import com.koke.constant.CommonConstants;

import com.koke.model.dto.RoleDTO;
import com.koke.model.dto.RoleMenuDTO;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.sys.role.Role;
import com.koke.model.entity.sys.role.RoleMenu;
import com.koke.service.inter.sys.role.RoleMenuService;
import com.koke.service.inter.sys.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理控制层
 * @author koke
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/role")
@Api(tags = "角色管理")
public class RoleController {

    private final RoleService roleService;
    private final RoleMenuService roleMenuService;

    @ApiOperation(value = "获取角色列表")
    @EnablePage
    @GetMapping
    public ResultInfo<List<Role>> getRoles(Role role) {
        List<Role> roles = roleService.selectRoles(role);
        return ResultInfo.success(roles);
    }

    @ApiOperation(value = "根据id删除角色信息")
    @GetMapping("/{roleId}")
    public ResultInfo<Role> getRoleById(@PathVariable("roleId") Long roleId) {
        Role role = roleService.selectRoleById(roleId);
        return ResultInfo.success(role);
    }

    @ApiOperation(value = "新增角色信息")
    @PostMapping
    public ResultInfo<Void> createRole(@RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
        return ResultInfo.success();
    }

    @ApiOperation(value = "更新角色信息")
    @PutMapping
    public ResultInfo<Void> updateRole(@RequestBody RoleDTO roleDTO) {
        roleService.updateRole(roleDTO);
        return ResultInfo.success();
    }

    @ApiOperation(value = "根据id列表删除角色信息")
    @DeleteMapping("/{roleIds}")
    public ResultInfo<Void> deleteRolesByIds(@PathVariable("roleIds") List<Long> roleIds) {
        roleService.deleteRolesByIds(roleIds);
        return ResultInfo.success();
    }

    @ApiOperation(value = "根据id获取角色拥有的菜单权限")
    @GetMapping("/menu/{roleId}")
    public ResultInfo<List<RoleMenu>> getRoleMenuByRoleId(@PathVariable("roleId") Long roleId) {
        List<RoleMenu> roleMenus = roleMenuService.selectRoleMenuByRoleId(roleId);
        return ResultInfo.success(roleMenus);
    }

    @ApiOperation(value = "给角色分配菜单权限")
    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    @PostMapping("/menu")
    public ResultInfo<Void> createRoleMenu(@Valid @RequestBody RoleMenuDTO roleMenuDTO) {
        roleMenuService.createRoleMenu(roleMenuDTO.getRoleId(), roleMenuDTO.getMenuIds());
        return ResultInfo.success();
    }

}
