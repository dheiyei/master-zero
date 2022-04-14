package com.lhj.service.inter;

import com.lhj.model.common.Tree;
import com.lhj.model.entity.Menu;

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
