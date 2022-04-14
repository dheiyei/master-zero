package com.lhj.utils;

import com.lhj.constant.CommonConstants;
import com.lhj.model.dto.CacheUserDTO;
import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class SecurityUtil {

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public CacheUserDTO getLoginUser() {
        String token = getAuthorizationToken();
        RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
        CacheUserDTO cacheUserDTO = (CacheUserDTO) redisTemplate.opsForValue().get(CommonConstants.USER_TOKEN + token);
        return cacheUserDTO;
    }

    /**
     * 移除登录信息
     */
    public void removeLoginUser() {
        String token = getAuthorizationToken();
        RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
        redisTemplate.delete(CommonConstants.USER_TOKEN + token);
    }

    /**
     * 从 jwt 中解析 token
     *
     * @return
     */
    private String getAuthorizationToken() {
        HttpServletRequest request = WebUtil.getHttpServletRequest();
        String jwt = JwtUtil.getJwtFromRequest(request);
        Claims claims = JwtUtil.parseJWT(jwt);
        return (String) claims.get(CommonConstants.AUTHORIZATION_TOKEN);
    }

}
