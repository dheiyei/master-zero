package com.koke.model.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志类
 * @author koke
 */
@Data
@ApiModel("日志对象")
public class OperateLog implements Serializable {

    private static final long serialVersionUID = 2316492667297984921L;

    @ApiModelProperty(value ="日志id",example = "0")
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

    @ApiModelProperty(value ="浏览器")
    private String browser;

    @ApiModelProperty(value ="操作系统")
    private String operatingSystem;

    @ApiModelProperty(value ="操作状态")
    private String operateState;

    @ApiModelProperty(value ="操作用户")
    private String operateUser;

    @ApiModelProperty(value ="操作时间")
    private LocalDateTime operateTime;

    @ApiModelProperty(value ="执行耗时",example = "0")
    private Long executeTime;

    @ApiModelProperty(value ="异常信息")
    private String exceptionMessage;

}
