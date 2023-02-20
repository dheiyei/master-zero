package com.koke.log.event.listener;

import com.koke.constant.LogState;
import com.koke.log.event.OperateLogEvent;
import com.koke.model.entity.OperateLog;
import com.koke.utils.LogUtil;
import com.koke.utils.SpringContextHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        OperateLog operateLog = LogUtil.getOperateLog("登录成功");
        operateLog.setOperateUser(authentication.getName());
        operateLog.setOperateState(LogState.SUCCESS.getState());
        Long startTime = System.currentTimeMillis();
        Long endTime = System.currentTimeMillis();
        operateLog.setExecuteTime(endTime - startTime);
        SpringContextHolder.publishEvent(new OperateLogEvent(operateLog));
    }

}
