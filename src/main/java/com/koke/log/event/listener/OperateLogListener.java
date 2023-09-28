package com.koke.log.event.listener;

import com.koke.log.event.OperateLogEvent;
import com.koke.model.entity.OperateLog;
import com.koke.service.inter.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 */
@RequiredArgsConstructor
public class OperateLogListener {

    private final OperateLogService operateLogService;

    @Async
    @Order
    @EventListener(OperateLogEvent.class)
    public void saveSysLog(OperateLogEvent event) {
        OperateLog operateLog = (OperateLog) event.getSource();
        operateLogService.createOperateLog(operateLog);
    }

}
