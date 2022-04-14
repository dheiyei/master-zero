package com.lhj.log.event;

import com.lhj.model.entity.OperateLog;
import org.springframework.context.ApplicationEvent;

/**
 * 操作日志事件
 */
public class OperateLogEvent extends ApplicationEvent {

    public OperateLogEvent(OperateLog source) {
        super(source);
    }

}
