package com.koke.model.entity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.koke.constant.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 公共返回类
 * @author koke
 */
@Getter
@Setter
@ApiModel(description= "公共返回对象")
public class ResultInfo<T> implements Serializable {

    private static final long serialVersionUID = 4297837488970680869L;

    @ApiModelProperty(value ="编号",example = "0")
    private Integer code;

    @ApiModelProperty(value ="提示消息")
    private String message;

    @ApiModelProperty(value ="响应数据")
    private T data;

    @ApiModelProperty(value ="总记录数",example = "0")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    /**
     * 请求成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> success() {
        return build(Status.OK.getCode(), Status.OK.getMessage(), null);
    }

    /**
     * 请求成功返回
     *
     * @param data 返回数据对象
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> success(T data) {
        return build(Status.OK.getCode(), Status.OK.getMessage(), data);
    }

    public static <T> ResultInfo<T> success(String message, T data) {
        return build(Status.OK.getCode(), message, data);
    }

    /**
     * 请求出错返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error() {
        return buildError(Status.INTERNAL_SERVER_ERROR.getCode(), Status.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    /**
     * 请求出错返回
     *
     * @param status
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(Status status) {
        return buildError(status.getCode(), status.getMessage(), null);
    }

    /**
     * 请求出错返回
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(String message) {
        return buildError(Status.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    /**
     * 请求出错返回
     *
     * @param errorCode 错误代码
     * @param message   错误提示信息
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(int errorCode, String message) {
        return buildError(errorCode, message, null);
    }

    /**
     * 请求出错返回
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(String message, T data) {
        return buildError(Status.INTERNAL_SERVER_ERROR.getCode(), message, data);
    }

    /**
     * 构建返回对象方法
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    private static <T> ResultInfo<T> build(Integer code, String message, T data) {
        if (code == null) {
            code = Status.OK.getCode();
        }
        if (message == null) {
            message = Status.OK.getMessage();
        }
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMessage(message);
        resultInfo.setData(data);
        return resultInfo;
    }


    private static <T> ResultInfo<T> buildError(Integer code, String message, T data) {
        if (code == null) {
            code = Status.INTERNAL_SERVER_ERROR.getCode();
        }
        if (message == null) {
            message = Status.INTERNAL_SERVER_ERROR.getMessage();
        }
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(code);
        resultInfo.setMessage(message);
        resultInfo.setData(data);
        return resultInfo;
    }

}
