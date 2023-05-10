package com.koke.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.koke.mapper.**")
@Configuration
public class MybatisConfig {

}
