package com.koke.aspect;

import com.koke.aspect.annotation.SysOperateLog;
import com.koke.constant.LogState;
import com.koke.log.event.OperateLogEvent;
import com.koke.model.entity.sys.OperateLog;
import com.koke.utils.LogUtil;
import com.koke.utils.SpringContextHolder;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class OperateLogAspect {

    @Pointcut("@annotation(com.koke.aspect.annotation.SysOperateLog)")
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
