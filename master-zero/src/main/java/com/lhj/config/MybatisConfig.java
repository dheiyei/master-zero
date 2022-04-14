package com.lhj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.lhj.mapper.**")
@Configuration
public class MybatisConfig {

}
