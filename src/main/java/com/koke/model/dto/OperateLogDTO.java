package com.koke.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志请求类
 * @author koke
 */
@Data
@ApiModel(description= "日志请求对象")
public class OperateLogDTO implements Serializable {

    private static final long serialVersionUID = -1292063890017358018L;

    @ApiModelProperty(value ="唯一ID",example = "0")
    private Long logId;

    @ApiModelProperty(value ="日志标题")
    private String title;

    @ApiModelProperty(value ="请求方式")
    private String requestMethod;

    @ApiModelProperty(value ="请求URI")
    private String requestUri;

    @ApiModelProperty(value ="请求参数")
    private String requestParams;

    @ApiModelProperty(value ="响应结果")
    private String responseResult;

    @ApiModelProperty(value ="ip地址")
    private String ipAddress;

    @ApiModelProperty(value ="操作状态")
    private String operateState;

    @ApiModelProperty(value ="操作用户")
    private String operateUser;

    @ApiModelProperty(value ="操作单位")
    private String operateUnit;

    @ApiModelProperty(value ="操作时间范围")
    private LocalDateTime[] operateTimes;

    @ApiModelProperty(value ="执行耗时",example = "0")
    private Long executeTime;

    @ApiModelProperty(value ="异常信息")
    private String exceptionMessage;

}
