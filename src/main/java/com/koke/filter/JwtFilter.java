package com.koke.filter;

import com.koke.config.IgnoreUrlsConfig;
import com.koke.constant.CommonConstants;
import com.koke.constant.Status;
import com.koke.exception.TokenException;
import com.koke.filter.handler.FilterExceptionHandler;
import com.koke.model.dto.CacheUserDTO;
import com.koke.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final FilterExceptionHandler filterExceptionHandler;
    private final RedisTemplate redisTemplate;
    private final IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (ignoreUrlsConfig.checkIgnores(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwtToken = JwtUtil.getJwtFromRequest(request);
        try {
            Claims claims = JwtUtil.parseJWT(jwtToken);
            setUpSpringAuthentication(claims);
        } catch (TokenException e) {
            filterExceptionHandler.writeError(response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 构造 Authentication
     *
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        String token = (String) claims.get(CommonConstants.AUTHORIZATION_TOKEN);
        CacheUserDTO cacheUserDTO = (CacheUserDTO) redisTemplate.opsForValue().get(CommonConstants.USER_TOKEN + token);
        if (cacheUserDTO != null) {
            Set<SimpleGrantedAuthority> authorities = cacheUserDTO.getAuthorities().stream().map(grantedAuthority -> new SimpleGrantedAuthority(grantedAuthority)).collect(Collectors.toSet());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            SecurityContextHolder.clearContext();
            throw new TokenException(Status.TOKEN_EXPIRED);
        }
    }

}
