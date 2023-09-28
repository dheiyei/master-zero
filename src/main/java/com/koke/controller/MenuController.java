package com.koke.controller;

import com.koke.constant.CommonConstants;
import com.koke.model.ResultInfo;
import com.koke.model.common.Tree;
import com.koke.model.entity.Menu;
import com.koke.service.inter.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResultInfo<List<Menu>> getMenus(Menu menu) {
        List<Menu> menus = menuService.getMenus(menu);
        return ResultInfo.success(menus);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + "'")
    @GetMapping("/tree")
    public ResultInfo<List<Tree>> getMenuTree(Menu menu) {
        List<Tree> menuTree = menuService.getMenuTree(menu);
        return ResultInfo.success(menuTree);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "#menuId")
    @GetMapping("/{menuId}")
    public ResultInfo<Menu> getMenuById(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.selectMenuById(menuId);
        return ResultInfo.success(menu);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, key = "#authentication.principal")
    @GetMapping("/user")
    public ResultInfo<List<Menu>> getMenusByUser(Authentication authentication) {
        List<Menu> menus = menuService.getMenusByUser();
        return ResultInfo.success(menus);
    }

    @Cacheable(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + ":'+(#authentication.principal)")
    @GetMapping("/user/tree")
    public ResultInfo<List<Tree>> getMenuTreeByUser(Authentication authentication) {
        List<Tree> menuTree = menuService.getMenuTreeByUser();
        return ResultInfo.success(menuTree);
    }

    @Caching(evict = {
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_MENU, key = "'" + CommonConstants.CACHE_KEY_TREE + "'"),
            @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    })
    @PostMapping
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
    public ResultInfo<Void> deleteMenuById(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenuById(menuId);
        return ResultInfo.success();
    }

}
