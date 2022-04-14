package com.lhj.log.event.listener;

import com.lhj.log.event.OperateLogEvent;
import com.lhj.model.entity.OperateLog;
import com.lhj.service.inter.OperateLogService;
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
