package com.koke.constant;

import lombok.Getter;

/**
 * 系统参数
 * @author koke
 */
@Getter
public enum Status {

    /**
     * 系统常规返回信息
     */
    OK(200, "操作成功"),
    UNAUTHORIZED(401, "您还没有登录，请先登录"),
    Authentication_ERROR(401, "认证异常"),
    FORBIDDEN(403, "您没有权限"),
    ACCOUNT_DISABLED(403, "当前用户状态异常，请联系管理员"),
    NOT_FOUND(404, "请求不存在"),
    METHOD_NOT_ALLOWED(405, "请求方式不支持"),
    INTERNAL_SERVER_ERROR(500, "服务器出错，请稍后重试或联系管理员"),
    USERNAME_PASSWORD_ERROR(1001, "用户名或密码错误"),
    TOKEN_EXPIRED(1001, "您的登录状态已过期，请重新登录"),
    TOKEN_PARSE_ERROR(1001, "您的登录状态已过期，请尝试重新登录！"),
    FILE_SIZE_EXCEED_LIMIT(1002, Constants.FILE_SIZE_EXCEED_LIMIT),
    /**
     * 单位 - 10000
     * Code 错误 - 10010
     */
    DEP_BE(10001, "单位存在"),
    DEP_PARENT_ACCOUNTING_END(10002, "上级单位为核算端"),
    DEP_DESCENDANT_BE_CODE(10003, "存在下级单位,不允许修改‘单位代码’"),
    DEP_DESCENDANT_BE_TYPE(10004, "存在下级单位,单位类型不能为‘核算端’"),
    DEP_CODE_NULL(10011, "单位代码值为空"),
    DEP_CODE_LEN_ERROR(10012, "单位代码长度错误"),
    DEP_CODE_FORMAT_ERROR(10013, "单位代码格式错误"),
    DEP_CODE_PARENT_NULL(10014, "缺少父级单位代码"),
    /**
     * 油料 - 20000
     * Code 错误 - 20010
     */
    OIL_BE(20001, "油料存在"),
    OIL_CODE_NULL(20011, "油料代码值为空"),
    OIL_CODE_LEN_ERROR(20012, "油料代码长度错误"),
    OIL_CODE_FORMAT_ERROR(20013, "油料代码格式错误"),
    OIL_CODE_PARENT_NULL(20014, "缺少父级油料代码"),

    /**
     * 经办人 - 30000
     */
    HANDLER_NOT_ACCOUNTING_END(30001, "新增失败,单位类型不是核算端"),


    /**
     * 空值报错 - 2000
     */
    HANDLER_NULL(2001, "报表中经办人为空"),
    UNIT_NULL(2002, "单位为空"),
    YEAR_NULL(2003, "年份为空"),
    MONTH_NULL(2004, "月份为空"),
    /**
     * 小品种报错 - 40000
     */
    SVR_STATE_NULL(40001,"状态为空"),

    /**
     * 附油报表报错 - 50000
     */
    SOR_NULL(50001,"当前无附油数据"),
    ;



    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 内容
     */
    private final String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
