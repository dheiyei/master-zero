package com.koke.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关白名单配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsConfig {

    private String[] urls;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public boolean checkIgnores(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        boolean flag = false;
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String url : urls) {
            if (pathMatcher.match(contextPath + url, requestURI)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
