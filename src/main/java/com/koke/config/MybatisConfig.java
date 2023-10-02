package com.koke.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mybatis配置
 */
@MapperScan("com.koke.mapper.**")
@Configuration
public class MybatisConfig {

}
