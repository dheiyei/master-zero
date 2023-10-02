package com.koke.aspect.annotation;

import java.lang.annotation.*;

/**
 * 系统操作日志
 * @author koke
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysOperateLog {

    /**
     * 日志标题
     * @return 日志
     */
    String value();

}
