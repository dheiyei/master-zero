package com.koke.log.event;

import com.koke.model.entity.OperateLog;
import org.springframework.context.ApplicationEvent;

/**
 * 操作日志事件
 */
public class OperateLogEvent extends ApplicationEvent {

    public OperateLogEvent(OperateLog source) {
        super(source);
    }

}
