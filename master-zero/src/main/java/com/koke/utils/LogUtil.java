package com.koke.utils;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.koke.model.entity.OperateLog;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@UtilityClass
public class LogUtil {

    /**
     * 获取操作日志信息
     *
     * @param title 日志标题
     * @return
     */
    public OperateLog getOperateLog(String title) {
        HttpServletRequest request = WebUtil.getHttpServletRequest();
        OperateLog operateLog = new OperateLog();
        operateLog.setTitle(title);
        operateLog.setRequestMethod(request.getMethod());
        operateLog.setRequestUri(request.getRequestURI());
        operateLog.setRequestParams(HttpUtil.toParams(request.getParameterMap()));
        operateLog.setIpAddress(ServletUtil.getClientIP(request));
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(HttpHeaders.USER_AGENT));
        operateLog.setBrowser(getBrowserWithVersion(userAgent));
        operateLog.setOperatingSystem(getOSWithVersion(userAgent));
        operateLog.setOperateUser(getUsername());
        operateLog.setOperateTime(LocalDateTime.now());
        return operateLog;
    }

    /**
     * 获取浏览器信息及版本
     *
     * @param userAgent
     * @return
     */
    private String getBrowserWithVersion(UserAgent userAgent) {
        String name = userAgent.getBrowser().getName();
        String version = userAgent.getVersion();
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(version)) {
            return name + " " + version;
        }
        return name;
    }

    /**
     * 获取操作系统信息及版本
     *
     * @param userAgent
     * @return
     */
    private String getOSWithVersion(UserAgent userAgent) {
        String name = userAgent.getPlatform().getName();
        String osVersion = userAgent.getOsVersion();
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(osVersion)) {
            return name + " " + osVersion;
        }
        return name;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

}
