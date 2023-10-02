package com.koke.controller.sys;

import com.koke.constant.CommonConstants;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.common.Tree;
import com.koke.model.entity.sys.Menu;
import com.koke.service.inter.sys.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理控制层
 * @author koke
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/menu")
@Api(tags = "菜单管理")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    @ApiOperation(value = "获取菜单列表")
    public ResultInfo<List<Menu>> getMenus(Menu menu) {
        List<Menu> menus = menuService.getMenus(menu);
        return ResultInfo.success(menus);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + "'")
    @GetMapping("/tree")
    @ApiOperation(value = "获取菜单树")
    public ResultInfo<List<Tree>> getMenuTree(Menu menu) {
        List<Tree> menuTree = menuService.getMenuTree(menu);
        return ResultInfo.success(menuTree);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "#menuId")
    @GetMapping("/{menuId}")
    @ApiOperation(value = "根据id获取菜单信息")
    public ResultInfo<Menu> getMenuById(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.selectMenuById(menuId);
        return ResultInfo.success(menu);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, key = "#authentication.principal")
    @GetMapping("/user")
    @ApiOperation(value = "根据登录的用户获取菜单")
    public ResultInfo<List<Menu>> getMenusByUser(Authentication authentication) {
        List<Menu> menus = menuService.getMenusByUser();
        return ResultInfo.success(menus);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + ":'+(#authentication.principal)")
    @GetMapping("/user/tree")
    @ApiOperation(value = "根据登录的用户获取菜单树")
    public ResultInfo<List<Tree>> getMenuTreeByUser(Authentication authentication) {
        List<Tree> menuTree = menuService.getMenuTreeByUser();
        return ResultInfo.success(menuTree);
    }

    @Caching(evict = {
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + "'"),
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    })
    @PostMapping
    @ApiOperation(value = "新增菜单信息")
    public ResultInfo<Void> createMenu(@Valid @RequestBody Menu menu) {
        menuService.createMenu(menu);
        return ResultInfo.success();
    }

    @Caching(evict = {
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + "'"),
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "#menu.menuId"),
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    })
    @PutMapping
    @ApiOperation(value = "更新菜单信息")
    public ResultInfo<Void> updateMenu(@Valid @RequestBody Menu menu) {
        menuService.updateMenu(menu);
        return ResultInfo.success();
    }

    @Caching(evict = {
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + "'"),
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "#menuId"),
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    })
    @DeleteMapping("/{menuId}")
    @ApiOperation(value = "根据id列表删除菜单信息")
    public ResultInfo<Void> deleteMenuById(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenuById(menuId);
        return ResultInfo.success();
    }

}
