package com.lhj.constant;

public interface CommonConstants {

    /**
     * 用户 id 字段
     */
    String USER_ID = "user_id";

    /**
     * 单位 id 字段
     */
    String UNIT_ID = "unit_id";

    /**
     * 角色前缀
     */
    String ROLE_PREFIX = "ROLE_";

    /**
     * 系统管理员角色
     */
    String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * 系统普通用户角色
     */
    String ROLE_USER = "ROLE_USER";

    /**
     * 树根节点
     */
    Long TREE_ROOT_ID = 0L;

    /**
     * jwt claim 中的 token
     */
    String AUTHORIZATION_TOKEN = "authorization_token";

    /**
     * redis 用户 token 前缀
     */
    String USER_TOKEN = "user_token:";

    /**
     * spring cache redis key 值
     */
    String CACHE_KEY_TREE = "tree";

    /**
     * spring cache redis 单位前缀
     */
    String CACHE_PREFIX_ADMIN_UNIT = "admin_unit";

    /**
     * spring cache redis 菜单前缀
     */
    String CACHE_PREFIX_ADMIN_MENU = "admin_menu";

    /**
     * spring cache redis 用户菜单前缀
     */
    String CACHE_PREFIX_ADMIN_USER_MENU = "admin_user_menu";

    /**
     * spring cache redis 字典前缀
     */
    String CACHE_PREFIX_ADMIN_DICT = "admin_dict";
    String CACHE_PREFIX_ADMIN_DICT_ITEM = "admin_dict_item";

    /**
     * spring cache redis 油品
     */
    String CACHE_PREFIX_OMS_OIL = "oms_oil";

    /**
     * spring cache redis 油品分类
     */
    String CACHE_PREFIX_OMS_OIL_CATEGORY = "oms_oil_category";

    /**
     * redis 油品 excel 前缀
     */
    String REDIS_EXCEL_OIL = "excel_oil:";

}
