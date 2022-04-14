package com.lhj.annotation;

import java.lang.annotation.*;

/**
 * 系统操作日志
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysOperateLog {

    /**
     * 日志标题
     *
     * @return
     */
    String value();

}
