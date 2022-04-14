package com.lhj.service.impl;

import com.google.common.collect.Maps;
import com.lhj.constant.CommonConstants;
import com.lhj.constant.MenuType;
import com.lhj.mapper.MenuMapper;
import com.lhj.model.common.Tree;
import com.lhj.model.common.TreeNode;
import com.lhj.model.common.TreeNodeConfig;
import com.lhj.model.dto.CacheUserDTO;
import com.lhj.model.entity.Menu;
import com.lhj.model.entity.role.Role;
import com.lhj.service.inter.MenuService;
import com.lhj.utils.SecurityUtil;
import com.lhj.utils.TreeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<Menu> getMenus(Menu menu) {
        return menuMapper.selectMenus(menu);
    }

    @Override
    public List<Tree> getMenuTree(Menu menu) {
        List<Menu> menus = menuMapper.selectMenus(menu);
        List<TreeNode> treeNodes = menus.stream().map(getMenuTreeNodeFunction()).collect(Collectors.toList());
        List<Tree> trees = TreeUtil.build(treeNodes, getTreeNodeConfig());
        return trees;
    }

    private Function<Menu, TreeNode> getMenuTreeNodeFunction() {
        return menu -> {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(menu.getMenuId());
            treeNode.setName(menu.getMenuName());
            treeNode.setParentId(menu.getParentId());
            treeNode.setSort(menu.getSort());
            HashMap<String, Object> extra = Maps.newHashMap();
            extra.put("url", menu.getUrl());
            extra.put("permission", menu.getPermission());
            extra.put("type", menu.getType());
            extra.put("icon", menu.getIcon());
            extra.put("createTime", menu.getCreateTime());
            extra.put("updateTime", menu.getUpdateTime());
            treeNode.setExtra(extra);
            return treeNode;
        };
    }

    private TreeNodeConfig getTreeNodeConfig() {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setId("menuId");
        treeNodeConfig.setName("menuName");
        return treeNodeConfig;
    }

    @Override
    public Menu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public List<Menu> getMenusByUser() {
        CacheUserDTO loginUser = SecurityUtil.getLoginUser();
        List<Role> roles = loginUser.getRoles();
        List<Menu> menus = new ArrayList<>();
        for (Role role : roles) {
            if (CommonConstants.ROLE_ADMIN.equals(role.getRoleKey())) {
                menus = menuMapper.selectMenus(null).stream().filter(getMenuPredicate()).collect(Collectors.toList());
                break;
            } else {
                menus = menuMapper.selectMenusByUserId(loginUser.getUserId()).stream().filter(getMenuPredicate()).collect(Collectors.toList());
            }
        }
        return menus;
    }

    /**
     * 过滤菜单类型
     *
     * @return
     */
    private Predicate<Menu> getMenuPredicate() {
        return menu -> MenuType.CONTENTS.getValue().equals(menu.getType()) || MenuType.MENU.getValue().equals(menu.getType());
    }

    @Override
    public List<Tree> getMenuTreeByUser() {
        List<Menu> menus = getMenusByUser();
        List<TreeNode> treeNodes = menus.stream().map(getMenuTreeNodeFunction()).collect(Collectors.toList());
        List<Tree> trees = TreeUtil.build(treeNodes, getTreeNodeConfig());
        return trees;
    }

    @Transactional
    @Override
    public void createMenu(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(CommonConstants.TREE_ROOT_ID);
        }
        menuMapper.createMenu(menu);
    }

    @Transactional
    @Override
    public void updateMenu(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(CommonConstants.TREE_ROOT_ID);
        }
        menuMapper.updateMenu(menu);
    }

    @Transactional
    @Override
    public void deleteMenuById(Long menuId) {
        menuMapper.deleteMenuById(menuId);
    }

}
