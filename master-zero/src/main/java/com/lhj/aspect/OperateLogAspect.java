package com.lhj.aspect;

import com.lhj.annotation.SysOperateLog;
import com.lhj.constant.LogState;
import com.lhj.log.event.OperateLogEvent;
import com.lhj.model.entity.OperateLog;
import com.lhj.utils.LogUtil;
import com.lhj.utils.SpringContextHolder;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OperateLogAspect {

    @Pointcut("@annotation(com.lhj.annotation.SysOperateLog)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(sysOperateLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint joinPoint, SysOperateLog sysOperateLog) {
        OperateLog operateLog = LogUtil.getOperateLog(sysOperateLog.value());
        long startTime = System.currentTimeMillis();
        Object proceed;
        try {
            operateLog.setOperateState(LogState.SUCCESS.getState());
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            operateLog.setOperateState(LogState.ERROR.getState());
            operateLog.setExceptionMessage(e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            operateLog.setExecuteTime(endTime - startTime);
            SpringContextHolder.publishEvent(new OperateLogEvent(operateLog));
        }
        return proceed;
    }

}
