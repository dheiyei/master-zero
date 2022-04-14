package com.lhj.mapper;

import com.lhj.model.entity.Menu;

import java.util.List;

public interface MenuMapper {

    List<Menu> selectMenus(Menu menu);

    List<Menu> selectMenusByUserId(Long userId);

    Menu selectMenuById(Long menuId);

    void createMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenuById(Long menuId);

}
