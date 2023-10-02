package com.koke.constant;

/**
 * 系统字段
 * @author koke
 */
public interface Constants {

    /**
     * 路径
     */
    String PATH_TEMPLATE = "template";
    String PATH = "/";
    String SPOT = ".";
    /**
     * 文件类型
     */
    String XLS = "xls";
    /**
     * 用户
     */
    String USERNAME_EXISTED = "用户名已存在";
    /**
     * 角色
     */
    String ROLE_KEY_EXISTED = "角色权限标识已存在";
    String SYS_ROLE_CAN_NOT_DELETE = "系统内置角色无法删除";
    String SYS_ROLE_KEY_CAN_NOT_UPDATE = "系统内置角色标识无法修改";

    /**
     * 单位
     */
    String UNIT_ID_NOT_EQUAL_PARENT_ID = "不能将当前单位设置成上级单位";
    String CHILD_UNIT_EXIST = "该单位有下级单位，请先删除子单位";
    String UNIT_NOT_EXIST = "该单位不存在";

    /**
     * 字典
     */
    String DICT_TYPE_EXISTED = "该字典类型已存在";
    String DICT_ITEM_EXISTED = "所选字典项有值，请先删除该字典";

    /**
     * 油品分类
     */
    String OIL_CATEGORY_ID_NOT_EQUAL_PARENT_ID = "不能将当前油品分类设置成上级油品分类";
    String CHILD_OIL_CATEGORY_EXIST = "该油品分类有下级油品分类，请先删除下级油品分类";
    String OIL_CATEGORY_EXIST = "该油品分类下有油品，请先删除该分类下的油品";
    String OIL_CATEGORY_EXCEED_LIMIT = "油品分类层级超出限制";

    /**
     * 油品类别
     */
    String OIL_TYPE_NAME_EXISTED = "油品类别名称已存在";

    /**
     * 油品
     */
    String OIL_NAME_EXISTED = "油品名称已存在";
    String OIL_CODE_EXISTED = "油品代码已存在";
    String OIL_REPLACEABLE_ID_NOT_EQUAL_OIL_ID = "不能将当前油品设置为可替代油品";

    /**
     * 油品提报
     */
    String SYSTEM_OIL_CAN_NOT_UPDATE = "系统油品无法修改";
    String REPORTED_OIL_CAN_NOT_UPDATE = "已提报油品无法修改";
    String REPORTED_OIL_CAN_NOT_DELETE = "已提报油品无法删除";


    /**
     * 产品规范
     */
    String STANDARD_NAME = "产品规范已存在";

    /**
     * 文件
     */
    String FILE_CONTENT_TYPE_ERROR = "文件格式错误";
    String FILE_SIZE_EXCEED_LIMIT = "文件大小超出限制";
    String FILE_CONTENT_ERROR = "文件内容不合法";
    String FILE_CONTENT_EMPTY = "文件内容为空";
    String FILE_UPLOAD_ERROR = "文件上传时出现错误，请重新上传";
    String EXCEL_IMPORT_ERROR = "EXCEL 导入时出现错误";

}
