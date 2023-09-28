package com.koke.utils;

import com.koke.constant.CommonConstants;
import com.koke.constant.Status;
import com.koke.exception.TokenException;
import com.koke.model.dto.CacheUserDTO;
import com.koke.model.entity.common.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@UtilityClass
public class JwtUtil {

//    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final Key key = Keys.hmacShaKeyFor("c086ee397f8043f58184af2e28df78b5".getBytes(StandardCharsets.UTF_8));

    public String createJWT(Long id, String subject, String token) {
        Date now = new Date();
        JwtBuilder builder = Jwts
                .builder()
                .setId(id.toString())
                .setSubject(subject)
                .setIssuedAt(now)
//                .signWith(key, SignatureAlgorithm.HS512)
                .signWith(key)
                .claim(CommonConstants.AUTHORIZATION_TOKEN, token);
        String jwt = builder.compact();
        return jwt;
    }

    public String createJWT(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = UUID.randomUUID().toString();
        //用户信息添加缓存
        RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set(CommonConstants.USER_TOKEN + token, CacheUserDTO.fromUserPrincipal.apply(userPrincipal), 604800000L, TimeUnit.MILLISECONDS);
        return createJWT(userPrincipal.getUserId(), userPrincipal.getUsername(), token);
    }

    public Claims parseJWT(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期: {}", e.getMessage());
            throw new TokenException(Status.TOKEN_EXPIRED);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名: {}", e.getMessage());
            throw new TokenException(Status.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 参数不存在: {}", e.getMessage());
            throw new TokenException(Status.TOKEN_PARSE_ERROR);
        } catch (UnsupportedJwtException e) {
            log.error("Token 无效: {}", e.getMessage());
            throw new TokenException(Status.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在: {}", e.getMessage());
            throw new TokenException(Status.UNAUTHORIZED);
        }
    }

    /**
     * 从请求头中获取 jwt
     *
     * @param request
     * @return
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        final String HEADER = "Authorization";
        final String PREFIX = "Bearer ";
        String bearerToken = request.getHeader(HEADER);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(PREFIX)) {
            return bearerToken.replace(PREFIX, "");
        }
        return null;
    }

}
