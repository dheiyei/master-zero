package com.koke.service.inter.sys;

import com.koke.model.entity.common.Tree;
import com.koke.model.entity.sys.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getMenus(Menu menu);

    List<Tree> getMenuTree(Menu menu);

    Menu selectMenuById(Long menuId);

    List<Menu> getMenusByUser();

    List<Tree> getMenuTreeByUser();

    void createMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenuById(Long menuId);

}
