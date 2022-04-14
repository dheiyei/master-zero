package com.lhj.log.event.listener;

import com.lhj.constant.LogState;
import com.lhj.constant.Status;
import com.lhj.log.event.OperateLogEvent;
import com.lhj.model.entity.OperateLog;
import com.lhj.utils.LogUtil;
import com.lhj.utils.SpringContextHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        OperateLog operateLog = LogUtil.getOperateLog("登录失败");
        operateLog.setOperateUser(authentication.getName());
        operateLog.setOperateState(LogState.ERROR.getState());
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        operateLog.setExecuteTime(endTime - startTime);
        operateLog.setExceptionMessage(getMessage(event));
        SpringContextHolder.publishEvent(new OperateLogEvent(operateLog));
    }

    private String getMessage(AbstractAuthenticationFailureEvent event) {
        String exceptionMessage;
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            exceptionMessage = Status.USERNAME_PASSWORD_ERROR.getMessage();
        } else {
            exceptionMessage = Status.ACCOUNT_DISABLED.getMessage();
        }
        return exceptionMessage;
    }

}
