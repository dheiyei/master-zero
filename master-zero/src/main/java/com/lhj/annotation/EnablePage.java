package com.lhj.annotation;
import java.lang.annotation.*;

/**
 * 开启分页
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnablePage {
}
