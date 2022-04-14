package com.lhj.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lhj.constant.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 公共返回对象
 */
@Getter
@Setter
public class ResultInfo<T> implements Serializable {

    private static final long serialVersionUID = 4297837488970680869L;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 总记录数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    /**
     * 请求成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> success() {
        ResultInfo<T> resultInfo = build(Status.OK.getCode(), Status.OK.getMessage(), null);
        return resultInfo;
    }

    /**
     * 请求成功返回
     *
     * @param data 返回数据对象
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> success(T data) {
        ResultInfo<T> resultInfo = build(Status.OK.getCode(), Status.OK.getMessage(), data);
        return resultInfo;
    }

    public static <T> ResultInfo<T> success(String message, T data) {
        ResultInfo<T> resultInfo = build(Status.OK.getCode(), message, data);
        return resultInfo;
    }

    /**
     * 请求出错返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error() {
        ResultInfo<T> resultInfo = buildError(Status.INTERNAL_SERVER_ERROR.getCode(), Status.INTERNAL_SERVER_ERROR.getMessage(), null);
        return resultInfo;
    }

    /**
     * 请求出错返回
     *
     * @param status
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(Status status) {
        ResultInfo<T> resultInfo = buildError(status.getCode(), status.getMessage(), null);
        return resultInfo;
    }

    /**
     * 请求出错返回
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultInfo<T> error(String message) {
        ResultInfo<T> resultInfo = buildError(Status.INTERNAL_SERVER_ERROR.getCode(), message, null);
        return resultInfo;
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
        ResultInfo<T> resultInfo = buildError(errorCode, message, null);
        return resultInfo;
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
        ResultInfo<T> resultInfo = buildError(Status.INTERNAL_SERVER_ERROR.getCode(), message, data);
        return resultInfo;
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
