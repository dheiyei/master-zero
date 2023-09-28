package com.koke.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OperateLogDTO implements Serializable {

    private static final long serialVersionUID = -1292063890017358018L;

    /**
     * 日志id
     */
    private Long logId;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应结果
     */
    private String responseResult;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 操作状态
     */
    private String operateState;

    /**
     * 操作用户
     */
    private String operateUser;

    /**
     * 操作单位
     */
    private String operateUnit;

    /**
     * 操作时间范围
     */
    private LocalDateTime[] operateTimes;

    /**
     * 执行耗时
     */
    private Long executeTime;

    /**
     * 异常信息
     */
    private String exceptionMessage;

}
