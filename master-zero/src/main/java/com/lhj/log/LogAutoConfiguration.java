package com.lhj.log;

import com.lhj.aspect.OperateLogAspect;
import com.lhj.log.event.listener.OperateLogListener;
import com.lhj.service.inter.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    @Bean
    public OperateLogListener operateLogListener(OperateLogService operateLogService) {
        return new OperateLogListener(operateLogService);
    }

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

}
