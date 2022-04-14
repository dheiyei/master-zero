package com.lhj.log.event.listener;

import com.lhj.constant.LogState;
import com.lhj.log.event.OperateLogEvent;
import com.lhj.model.entity.OperateLog;
import com.lhj.utils.LogUtil;
import com.lhj.utils.SpringContextHolder;
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
