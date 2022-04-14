package com.lhj.exception;

import com.lhj.constant.Status;
import com.lhj.model.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultInfo<Map<String, String>> handlerException(Exception e) {
        log.error("异常: {}", e);
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(e.getMessage());
        return resultInfo;
    }

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResultInfo<Map<String, String>> handlerCustomException(CustomException e) {
        log.error("异常: {}", e);
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(e.getCode(), e.getMessage());
        return resultInfo;
    }

    /**
     * 认证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResultInfo<Map<String, String>> handlerAuthenticationException(AuthenticationException e) {
        log.error("认证异常：{}", e);
        if (e instanceof BadCredentialsException) {
            return ResultInfo.error(Status.USERNAME_PASSWORD_ERROR);
        }
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(Status.Authentication_ERROR);
        return resultInfo;
    }

    /**
     * 鉴权异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResultInfo<Map<String, String>> handlerAccessDeniedException(AccessDeniedException e) {
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(Status.FORBIDDEN);
        return resultInfo;
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ResultInfo<Map<String, String>> BindExceptionHandler(BindException e) {
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(e.getBindingResult().getFieldError().getDefaultMessage());
        return resultInfo;
    }

    /**
     * 账号状态异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccountStatusException.class)
    public ResultInfo<Map<String, String>> handlerAccountStatusException(AccountStatusException e) {
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(Status.ACCOUNT_DISABLED);
        return resultInfo;
    }

    /**
     * 文件超出大小异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResultInfo<Map<String, String>> handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        ResultInfo<Map<String, String>> resultInfo = ResultInfo.error(Status.FILE_SIZE_EXCEED_LIMIT);
        return resultInfo;
    }

}
