package com.koke.aspect.annotation;
import java.lang.annotation.*;

/**
 * 开启分页
 * @author koke
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnablePage {
}
