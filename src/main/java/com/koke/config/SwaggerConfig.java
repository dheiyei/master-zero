package com.koke.config;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration // 标明是配置类
@EnableSwagger2 //开启swagger功能
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        // DocumentationType.SWAGGER_2 固定的，代表swagger2
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("分布式任务系统") // 如果配置多个文档的时候，那么需要配置groupName来分组标识
                // 用于生成API信息
                .apiInfo(apiInfo())
                // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                .select()
                // 用于指定扫描哪个包下的接口
                .apis(RequestHandlerSelectors.basePackage("com.koke.controller"))
                // 选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());

    }

    /**
     * 用于定义API主界面的信息，比如可以声明所有的API的总标题、描述、版本
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("海军能源信息综合管理系统") //  可以用来自定义API的主标题
                .description("海军能源信息综合管理系统API管理") // 可以用来描述整体的API
                .termsOfServiceUrl("127.0.0.1") // 用于定义服务的域名
                .version("1.0") // 可以用来定义版本。
                .build();
    }

}
